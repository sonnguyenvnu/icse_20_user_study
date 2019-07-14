public static PathParams single(String key,Object value){
  return new PathParams().add(key,value.toString());
}
