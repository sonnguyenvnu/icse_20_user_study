private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter){
  return new DefaultDataSourceFactory(this,useBandwidthMeter ? BANDWIDTH_METER : null,buildHttpDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null));
}
