@JsonIgnore private void parse(){
  if (latestCheckDataFeeds != null && !latestCheckDataFeeds.isEmpty()) {
    this.totalCount=latestCheckDataFeeds.size();
    Date minCompleteDate=null;
    for (    CheckDataJob job : latestCheckDataFeeds) {
      if (minCompleteDate == null || minCompleteDate.getTime() < job.getEndTime().getMillis()) {
        minCompleteDate=job.getEndTime().toDate();
      }
      if (job.isValid()) {
        this.successCount++;
      }
 else {
        this.failedCount++;
        this.failedJobs.add(job);
        warnings.add(job.getFeedName() + " failed Validation at " + job.getEndTime() + " because: " + job.getValidationMessage());
      }
    }
    Date now=new Date();
    if (minCompleteDate == null) {
      throw new RuntimeException("The minimum complete date was not set as expected");
    }
    long duration=now.getTime() - minCompleteDate.getTime();
    long diffInMinutes=TimeUnit.MILLISECONDS.toMinutes(duration);
    this.timeSinceLastCheck=diffInMinutes + " min ";
    if (diffInMinutes == 0) {
      long diffInSeconds=TimeUnit.MILLISECONDS.toSeconds(duration);
      this.timeSinceLastCheck=diffInSeconds + " sec ";
    }
    this.confidencePercentage=((float)successCount / totalCount) * 100;
    if (this.confidencePercentage > 0) {
      DecimalFormat twoDForm=new DecimalFormat("##.##");
      this.confidencePercentage=Float.valueOf(twoDForm.format(this.confidencePercentage));
    }
    if (this.warningTimeThreshold != null && diffInMinutes > this.warningTimeThreshold) {
      warnings.add(" Warning it has been over " + this.warningTimeThreshold + " minutes since some of the check data jobs have been run");
    }
    this.minCompleteDate=minCompleteDate;
  }
 else {
    warnings.add("No Check Data Jobs Found");
  }
}
