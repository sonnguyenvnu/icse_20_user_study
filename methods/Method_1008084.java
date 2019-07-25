@Override public EvalQueryQuality evaluate(String taskId,SearchHit[] hits,List<RatedDocument> ratedDocs){
  List<Integer> allRatings=ratedDocs.stream().mapToInt(RatedDocument::getRating).boxed().collect(Collectors.toList());
  List<RatedSearchHit> ratedHits=joinHitsWithRatings(hits,ratedDocs);
  List<Integer> ratingsInSearchHits=new ArrayList<>(ratedHits.size());
  for (  RatedSearchHit hit : ratedHits) {
    ratingsInSearchHits.add(hit.getRating().orElse(unknownDocRating));
  }
  double dcg=computeDCG(ratingsInSearchHits);
  if (normalize) {
    Collections.sort(allRatings,Comparator.nullsLast(Collections.reverseOrder()));
    double idcg=computeDCG(allRatings.subList(0,Math.min(ratingsInSearchHits.size(),allRatings.size())));
    dcg=dcg / idcg;
  }
  EvalQueryQuality evalQueryQuality=new EvalQueryQuality(taskId,dcg);
  evalQueryQuality.addHitsAndRatings(ratedHits);
  return evalQueryQuality;
}
