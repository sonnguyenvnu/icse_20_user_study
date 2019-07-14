public HttpClientGenerator setPoolSize(int poolSize){
  connectionManager.setMaxTotal(poolSize);
  return this;
}
