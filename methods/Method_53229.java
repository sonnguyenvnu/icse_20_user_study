private void appendParameter(String name,long value,List<HttpParameter> params){
  if (0 <= value) {
    params.add(new HttpParameter(name,String.valueOf(value)));
  }
}
