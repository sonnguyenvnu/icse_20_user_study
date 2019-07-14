/** 
 * Sets bean parameters from bean. Non-existing bean properties are ignored.
 */
public Q setBean(final String beanName,final Object bean){
  if (bean == null) {
    return _this();
  }
  init();
  final String beanNamePrefix=beanName + '.';
  query.forEachNamedParameter(p -> {
    final String paramName=p.name;
    if (paramName.startsWith(beanNamePrefix)) {
      final String propertyName=paramName.substring(beanNamePrefix.length());
      if (BeanUtil.declared.hasRootProperty(bean,propertyName)) {
        final Object value=BeanUtil.declared.getProperty(bean,propertyName);
        setObject(paramName,value);
      }
    }
  }
);
  return _this();
}
