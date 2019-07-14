/** 
 * Resolves bean's name from bean annotation or type name. May be used for resolving bean name of base type during registration of bean subclass.
 */
public String resolveBeanName(final Class type,final boolean useLongTypeName){
  PetiteBean petiteBean=((Class<?>)type).getAnnotation(PetiteBean.class);
  String name=null;
  if (petiteBean != null) {
    name=petiteBean.value().trim();
  }
  if ((name == null) || (name.length() == 0)) {
    if (useLongTypeName) {
      name=type.getName();
    }
 else {
      name=StringUtil.uncapitalize(type.getSimpleName());
    }
  }
  return name;
}
