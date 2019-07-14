HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter){
  return new DefaultHttpDataSourceFactory(Util.getUserAgent(this,"LeafPic"),bandwidthMeter);
}
