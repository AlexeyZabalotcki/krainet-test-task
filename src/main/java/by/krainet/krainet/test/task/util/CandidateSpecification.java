//package by.krainet.krainet.test.task.util;
//
//import by.krainet.krainet.test.task.model.Candidate;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import org.springframework.data.jpa.domain.Specification;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CandidateSpecification implements Specification<Candidate> {
//
//    private final List<SearchCriteria> criteriaList;
//
//    public CandidateSpecification() {
//        this.criteriaList = new ArrayList<>();
//    }
//
//    public void add(SearchCriteria criteria) {
//        criteriaList.add(criteria);
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//        List<Predicate> predicates = new ArrayList<>();
//        for (SearchCriteria criteria : criteriaList) {
//            if (criteria.getOperation().equalsIgnoreCase(">")) {
//                predicates.add(builder.greaterThanOrEqualTo(
//                        root.get(criteria.getKey()), criteria.getValue().toString()));
//            } else if (criteria.getOperation().equalsIgnoreCase("<")) {
//                predicates.add(builder.lessThanOrEqualTo(
//                        root.get(criteria.getKey()), criteria.getValue().toString()));
//            } else if (criteria.getOperation().equalsIgnoreCase(":")) {
//                if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                    predicates.add(builder.like(
//                            root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
//                } else {
//                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
//                }
//            }
//        }
//        return builder.and(predicates.toArray(new Predicate[0]));
//    }
//}
