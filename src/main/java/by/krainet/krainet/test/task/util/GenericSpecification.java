package by.krainet.krainet.test.task.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Attribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private final String keyword;

    public GenericSpecification(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (keyword == null || keyword.isEmpty()) {
            return builder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();
        for (Attribute<? super T, ?> attr : root.getModel().getAttributes()) {
            if (attr.getPersistentAttributeType() == Attribute.PersistentAttributeType.BASIC) {
                String attributeName = attr.getName();
                Path<String> path = root.get(attributeName);
                if (path.getJavaType() == String.class) {
                    predicates.add(builder.like(builder.lower(path), "%" + keyword.toLowerCase() + "%"));
                }
            }
        }

        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
