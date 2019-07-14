public T getResource(){
  try {
    return internalPool.borrowObject();
  }
 catch (  Exception e) {
    throw new JedisConnectionException("Could not get a resource from the pool",e);
  }
}
