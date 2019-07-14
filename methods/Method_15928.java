public MapperEntityFactory addCopier(PropertyCopier copier){
  Class source=ClassUtils.getGenericType(copier.getClass(),0);
  Class target=ClassUtils.getGenericType(copier.getClass(),1);
  if (source == null || source == Object.class) {
    throw new UnsupportedOperationException("generic type " + source + " not support");
  }
  if (target == null || target == Object.class) {
    throw new UnsupportedOperationException("generic type " + target + " not support");
  }
  addCopier(source,target,copier);
  return this;
}
