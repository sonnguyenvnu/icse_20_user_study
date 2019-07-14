public void takeProjectAndStats(final @NonNull Pair<Project,List<ProjectStatsEnvelope.RewardStats>> projectAndRewards){
  sections().clear();
  addSection(Observable.from(projectAndRewards.second).map(rewardStats -> Pair.create(projectAndRewards.first,rewardStats)).toList().toBlocking().single());
  notifyDataSetChanged();
}
