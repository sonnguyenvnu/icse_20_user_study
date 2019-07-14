@Override protected int poolSize(){
  return mPoolSize == UNSPECIFIED_POOL_SIZE ? super.poolSize() : mPoolSize;
}
