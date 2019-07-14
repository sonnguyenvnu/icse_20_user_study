protected DictionaryWrapperObject createCache(Class bean){
  String beanName=bean.getName();
  StringBuilder wrapMethod=new StringBuilder().append("public void wrap(Object id,Object bean, org.hswebframework.web.dictionary.simple.DefaultDictionaryHelper helper)").append("{\n").append(bean.getName()).append(" target=(").append(bean.getName()).append(")bean;\n");
  StringBuilder persistentMethod=new StringBuilder().append("public void persistent(Object id,Object bean, org.hswebframework.web.dictionary.simple.DefaultDictionaryHelper helper)").append("{\n").append(bean.getName()).append(" target=(").append(bean.getName()).append(")bean;\n");
  PropertyDescriptor[] descriptors=BeanUtilsBean.getInstance().getPropertyUtils().getPropertyDescriptors(bean);
  boolean hasDict=false;
  for (  PropertyDescriptor descriptor : descriptors) {
    Class type=descriptor.getPropertyType();
    boolean isArray=type.isArray();
    if (isArray) {
      type=type.getComponentType();
    }
    if (type.isEnum() && EnumDict.class.isAssignableFrom(type) && type.getEnumConstants().length >= 64) {
      String typeName=isArray ? type.getName().concat("[]") : type.getName();
      String dictId=type.getName();
      Dict dict=(Dict)type.getAnnotation(Dict.class);
      if (dict != null) {
        dictId=dict.id();
      }
      wrapMethod.append("{\n");
      wrapMethod.append(typeName).append(" dict=(").append(typeName).append(")helper.getDictEnum(id,").append("\"").append(beanName).append(".").append(descriptor.getName()).append("\"").append(",\"").append(dictId).append("\"").append(",").append(typeName).append(".class);\n");
      wrapMethod.append("target.").append(descriptor.getWriteMethod().getName()).append("(dict);\n");
      wrapMethod.append("}");
      persistentMethod.append("helper.persistent(id,").append("\"").append(beanName).append(".").append(descriptor.getName()).append("\"").append(",\"").append(dictId).append("\"").append(",").append(typeName).append(".class,").append("target.").append(descriptor.getReadMethod().getName()).append("()").append(");\n");
      hasDict=true;
    }
  }
  wrapMethod.append("\n}");
  persistentMethod.append("\n}");
  if (hasDict) {
    return Proxy.create(DictionaryWrapperObject.class).addMethod(wrapMethod.toString()).addMethod(persistentMethod.toString()).newInstance();
  }
  return EMPTY_WRAPPER;
}
