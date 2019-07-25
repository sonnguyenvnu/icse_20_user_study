private Predicate augment(QOpsManagerFeedId id){
  return FeedAclIndexQueryAugmentor.generateExistsExpression(id,controller.isEntityAccessControlled());
}
