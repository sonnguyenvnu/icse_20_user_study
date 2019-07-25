@JsonIgnore private void serialize(Feed feed){
  if (feed.getDependentFeeds() != null) {
    Set<String> ids=new HashSet<>();
    Set<Feed> dependentFeeds=new HashSet<>(feed.getDependentFeeds());
    feed.setDependentFeeds(null);
    dependentFeeds.stream().forEach(depFeed -> {
      feedMap.put(depFeed.getId(),depFeed);
      ids.add(depFeed.getId());
      if (depFeed.getDependentFeeds() != null) {
        serialize(depFeed);
      }
    }
);
    feed.setDependentFeedIds(ids);
  }
  if (feed.getUsedByFeeds() != null) {
    Set<String> ids=new HashSet<>();
    Set<Feed> usedByFeeds=new HashSet<>(feed.getUsedByFeeds());
    feed.getUsedByFeeds().clear();
    usedByFeeds.stream().forEach(depFeed -> {
      feedMap.put(depFeed.getId(),depFeed);
      ids.add(depFeed.getId());
      if (depFeed.getUsedByFeeds() != null) {
        serialize(depFeed);
      }
    }
);
    feed.setUsedByFeedIds(ids);
  }
  if (feed.getSources() != null) {
    feed.getSources().forEach(feedSource -> {
      Datasource ds=serializeDatasource(feedSource.getDatasource());
      feedSource.setDatasource(null);
      if (StringUtils.isBlank(feedSource.getDatasourceId())) {
        feedSource.setDatasourceId(ds != null ? ds.getId() : null);
      }
    }
);
  }
  if (feed.getDestinations() != null) {
    feed.getDestinations().forEach(feedDestination -> {
      Datasource ds=serializeDatasource(feedDestination.getDatasource());
      feedDestination.setDatasource(null);
      if (StringUtils.isBlank(feedDestination.getDatasourceId())) {
        feedDestination.setDatasourceId(ds != null ? ds.getId() : null);
      }
    }
);
  }
  feedMap.put(feed.getId(),feed);
}
