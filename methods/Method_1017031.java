@Override public boolean apply(Series series){
  return series.getTags().containsKey(tag());
}
