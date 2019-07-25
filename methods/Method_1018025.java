@Nonnull @Override public TransformResult apply(@Nullable final TransformResult result){
  Preconditions.checkNotNull(result);
  ProfilerConfiguration profilerConfiguration=new ProfilerConfiguration();
  profilerConfiguration.setNumberOfTopNValues(50);
  profilerConfiguration.setBins(35);
  final StatisticsModel dataStats=profiler.profile(result.getDataSet(),profilerConfiguration);
  if (dataStats != null) {
    final List<OutputRow> profile=(result.getProfile() != null) ? new ArrayList<>(result.getProfile()) : new ArrayList<OutputRow>(dataStats.getColumnStatisticsMap().size());
    for (    final ColumnStatistics columnStats : dataStats.getColumnStatisticsMap().values()) {
      profile.addAll(columnStats.getStatistics());
    }
    result.setProfile(profile);
  }
  return result;
}
