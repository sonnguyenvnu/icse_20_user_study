@Override public Response<Double> geodist(byte[] key,byte[] member1,byte[] member2,GeoUnit unit){
  getClient(key).geodist(key,member1,member2,unit);
  return getResponse(BuilderFactory.DOUBLE);
}
