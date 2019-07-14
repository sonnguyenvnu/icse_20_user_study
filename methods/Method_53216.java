private void appendParameter(String name,String value,List<HttpParameter> params){
  if (value != null) {
    params.add(new HttpParameter(name,value));
  }
}
