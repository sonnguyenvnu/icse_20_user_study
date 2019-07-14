public void takeProjectAndReferrerStats(final @NonNull Pair<Project,List<ProjectStatsEnvelope.ReferrerStats>> projectAndReferrerStats){
  sections().clear();
  addSection(Observable.from(projectAndReferrerStats.second).map(referrerStats -> Pair.create(projectAndReferrerStats.first,referrerStats)).toList().toBlocking().single());
  notifyDataSetChanged();
}
