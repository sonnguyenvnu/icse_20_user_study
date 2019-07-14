public Object getPropertyValueDirect(Object object) throws InvocationTargetException, IllegalAccessException {
  Object fieldValue=fieldInfo.get(object);
  if (persistenceXToMany && !TypeUtils.isHibernateInitialized(fieldValue)) {
    return null;
  }
  return fieldValue;
}
