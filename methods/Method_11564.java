@Override public String get(){
  if (CollectionUtils.isNotEmpty(all())) {
    return all().get(0);
  }
 else {
    return null;
  }
}
