protected void putParameters(final String name,final String[] values){
  if (requestParameters == null) {
    requestParameters=new HashMap<>();
  }
  requestParameters.put(name,values);
}
