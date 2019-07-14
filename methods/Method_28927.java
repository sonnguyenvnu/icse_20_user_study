public void initPool(final GenericObjectPoolConfig poolConfig,PooledObjectFactory<T> factory){
  if (this.internalPool != null) {
    try {
      closeInternalPool();
    }
 catch (    Exception e) {
    }
  }
  this.internalPool=new GenericObjectPool<T>(factory,poolConfig);
}
