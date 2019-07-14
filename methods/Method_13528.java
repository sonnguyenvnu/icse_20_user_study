@Override @Path("param") @GET public String param(@QueryParam("param") String param){
  log("/param",param);
  return param;
}
