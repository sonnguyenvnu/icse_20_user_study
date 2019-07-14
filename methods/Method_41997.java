private HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter){
  return new DefaultHttpDataSourceFactory(Util.getUserAgent(this,"LeafPic"),useBandwidthMeter ? BANDWIDTH_METER : null);
}
