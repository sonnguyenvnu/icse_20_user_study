public void dec(int numConsumedBytes){
  final int oldValue=getAndAdd(-numConsumedBytes);
  if (oldValue > lowWatermark && oldValue - numConsumedBytes <= lowWatermark) {
    if (cfg != null) {
      cfg.setAutoRead(true);
      suspended=false;
    }
  }
}
