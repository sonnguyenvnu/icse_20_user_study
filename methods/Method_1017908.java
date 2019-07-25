@Override public <S,T,ID extends Serializable>Specification<S> augment(Specification<S> spec,Class<S> domainClass,JpaEntityInformation<T,ID> entityInformation){
  LOG.debug("QueryAugmentor.augment");
  return (root,query,criteriaBuilder) -> {
    Root<JpaFeedOpsAclEntry> fromAcl=query.from(JpaFeedOpsAclEntry.class);
    query.distinct(true);
    if (query.getSelection() == null) {
      query.select((Selection)root);
    }
    Path<Object> feedId=getFeedId(entityInformation,root);
    javax.persistence.criteria.Predicate rootFeedIdEqualToAclFeedId=criteriaBuilder.equal(feedId,fromAcl.get("feedId"));
    RoleSetExposingSecurityExpressionRoot userCxt=getUserContext();
    javax.persistence.criteria.Predicate aclPrincipalInGroups=fromAcl.get("principalName").in(userCxt.getGroups());
    javax.persistence.criteria.Predicate aclPrincipalTypeIsGroup=criteriaBuilder.equal(fromAcl.get("principalType"),FeedOpsAclEntry.PrincipalType.GROUP);
    javax.persistence.criteria.Predicate acePrincipalGroupMatch=criteriaBuilder.and(aclPrincipalInGroups,aclPrincipalTypeIsGroup);
    javax.persistence.criteria.Predicate aclPrincipalEqUser=criteriaBuilder.equal(fromAcl.get("principalName"),userCxt.getName());
    javax.persistence.criteria.Predicate aclPrincipalTypeIsUser=criteriaBuilder.equal(fromAcl.get("principalType"),FeedOpsAclEntry.PrincipalType.USER);
    javax.persistence.criteria.Predicate acePrincipalUserMatch=criteriaBuilder.and(aclPrincipalEqUser,aclPrincipalTypeIsUser);
    javax.persistence.criteria.Predicate acePrincipalMatch=criteriaBuilder.or(acePrincipalGroupMatch,acePrincipalUserMatch);
    javax.persistence.criteria.Predicate feedIdEqualsAndPrincipalMatch=criteriaBuilder.and(rootFeedIdEqualToAclFeedId,acePrincipalMatch);
    if (spec != null) {
      javax.persistence.criteria.Predicate predicate=spec.toPredicate(root,query,criteriaBuilder);
      return criteriaBuilder.and(predicate,feedIdEqualsAndPrincipalMatch);
    }
 else {
      return feedIdEqualsAndPrincipalMatch;
    }
  }
;
}
