protected void returnBrokenResource(final Jedis resource){
  if (resource != null) {
    returnBrokenResourceObject(resource);
  }
}
