@Override public Response<Double> geodist(String key,String member1,String member2,GeoUnit unit){
  getClient(key).geodist(key,member1,member2);
  return getResponse(BuilderFactory.DOUBLE);
}
