protected DictWrapper createCache(Class bean){
  StringBuilder method=new StringBuilder().append("public void wrap(Object bean, org.hswebframework.web.dict.DictDefineRepository repository)").append("{\n").append(bean.getName()).append(" target=(").append(bean.getName()).append(")bean;\n");
  ReflectionUtils.doWithFields(bean,field -> {
    Class type=field.getType();
    if (type.isArray()) {
      type=type.getComponentType();
    }
    if (type.isEnum() && EnumDict.class.isAssignableFrom(type) && type.getEnumConstants().length >= 64) {
    }
  }
);
  method.append("\n}");
  return DictWrapper.empty;
}
