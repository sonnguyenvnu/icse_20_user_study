@Override public void record(TagContext tags){
  if (hasUnsupportedValues) {
    logger.log(Level.WARNING,"Dropping values, value to record must be non-negative.");
    return;
  }
  statsManager.record(tags,builder.build());
}
