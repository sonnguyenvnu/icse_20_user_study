/** 
 * Compute precisionAtN based on provided relevant document IDs.
 * @return precision at n for above {@link SearchResult} list.
 */
@Override public EvalQueryQuality evaluate(String taskId,SearchHit[] hits,List<RatedDocument> ratedDocs){
  int truePositives=0;
  int falsePositives=0;
  List<RatedSearchHit> ratedSearchHits=joinHitsWithRatings(hits,ratedDocs);
  for (  RatedSearchHit hit : ratedSearchHits) {
    Optional<Integer> rating=hit.getRating();
    if (rating.isPresent()) {
      if (rating.get() >= this.relevantRatingThreshhold) {
        truePositives++;
      }
 else {
        falsePositives++;
      }
    }
 else     if (ignoreUnlabeled == false) {
      falsePositives++;
    }
  }
  double precision=0.0;
  if (truePositives + falsePositives > 0) {
    precision=(double)truePositives / (truePositives + falsePositives);
  }
  EvalQueryQuality evalQueryQuality=new EvalQueryQuality(taskId,precision);
  evalQueryQuality.setMetricDetails(new PrecisionAtK.Breakdown(truePositives,truePositives + falsePositives));
  evalQueryQuality.addHitsAndRatings(ratedSearchHits);
  return evalQueryQuality;
}
