@Override protected void returnBrokenResource(final ShardedJedis resource){
  if (resource != null) {
    returnBrokenResourceObject(resource);
  }
}
