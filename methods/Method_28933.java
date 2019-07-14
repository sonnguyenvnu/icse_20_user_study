public void addObjects(int count){
  try {
    for (int i=0; i < count; i++) {
      this.internalPool.addObject();
    }
  }
 catch (  Exception e) {
    throw new JedisException("Error trying to add idle objects",e);
  }
}
