private void appendParameter(String name,double value,List<HttpParameter> params){
  params.add(new HttpParameter(name,String.valueOf(value)));
}
