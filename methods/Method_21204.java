public void takeProjectAndStats(final @NonNull Pair<Project,ProjectStatsEnvelope> projectAndStatsEnvelope){
  setSection(SECTION_FUNDING_VIEW,Collections.singletonList(projectAndStatsEnvelope));
  setSection(SECTION_REWARD_STATS_VIEW,Collections.singletonList(Pair.create(projectAndStatsEnvelope.first,projectAndStatsEnvelope.second.rewardDistribution())));
  setSection(SECTION_REFERRER_BREAKDOWN_LAYOUT,Collections.singletonList(Pair.create(projectAndStatsEnvelope.first,projectAndStatsEnvelope.second)));
  setSection(SECTION_REFERRER_STATS_VIEW,Collections.singletonList(Pair.create(projectAndStatsEnvelope.first,projectAndStatsEnvelope.second.referralDistribution())));
  notifyDataSetChanged();
}
