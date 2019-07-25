/** 
 * Create/Update the Relationship between a SLA and a Set of Feeds
 */
@Override public FeedServiceLevelAgreementRelationship relate(ServiceLevelAgreement sla,Set<Feed.ID> feedIds){
  Set<Feed> feeds=feedIds.stream().map(id -> feedProvider.getFeed(id)).filter(feed -> feed != null).collect(Collectors.toSet());
  return relateFeeds(sla,feeds);
}
