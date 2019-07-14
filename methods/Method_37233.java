@Benchmark public Object joddBean(){
  return BeanUtil.declared.getProperty(javaBean,fieldName);
}
