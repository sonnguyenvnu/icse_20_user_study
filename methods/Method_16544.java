public static SimplePersonnelAuthentication fromMap(Map<String,Object> map){
  return FastBeanCopier.copy(map,new SimplePersonnelAuthentication(),converter);
}
