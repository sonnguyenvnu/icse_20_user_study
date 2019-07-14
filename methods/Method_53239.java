private void appendParameter(String name,long value,List<HttpParameter> params){
  params.add(new HttpParameter(name,String.valueOf(value)));
}
