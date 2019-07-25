private void populate(){
  this.healthyFeeds=new ArrayList<FeedHealth>();
  this.failedFeeds=new ArrayList<FeedHealth>();
  this.feedSummary=new ArrayList<>();
  healthyCount=0;
  failedCount=0;
  percent=0f;
  if (feeds != null && !feeds.isEmpty()) {
    for (    FeedHealth feedHealth : feeds) {
      if (feedHealth.isHealthy()) {
        healthyCount++;
        healthyFeeds.add(feedHealth);
      }
 else {
        failedCount++;
        failedFeeds.add(feedHealth);
      }
      this.feedSummary.add(new DefaultFeedSummary(feedHealth));
    }
    percent=(float)healthyCount / feeds.size();
  }
  if (percent > 0f) {
    DecimalFormat twoDForm=new DecimalFormat("##.##");
    try {
      this.percent=twoDForm.parse(twoDForm.format(this.percent)).floatValue() * 100;
    }
 catch (    ParseException e) {
      log.error("Error parsing percent value ",e);
    }
  }
}
