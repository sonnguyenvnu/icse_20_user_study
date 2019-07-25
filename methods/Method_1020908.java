public JPABase create(Class clzz,Map<String,Object> params) throws Exception {
  Object o=clzz.newInstance();
  ParamBinding paramBinding=new ParamBinding();
  paramBinding.parse(params);
  paramBinding.toModel(o);
  return ((Model)o);
}
