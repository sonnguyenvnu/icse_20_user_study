private Predicate<FeedSummary> filter(FeedSummaryFilter filter,RoleSetExposingSecurityExpressionRoot userContext){
  return s -> {
    try {
      return feedAclCache.hasAccess(userContext,s.getFeedId().toString()) && fixedFilter(s,filter) && (filter.containsFeed(s.getFeedName()) && filter.containsState(s.getRunStatus().name().toLowerCase()));
    }
 catch (    Exception e) {
      return false;
    }
  }
;
}
