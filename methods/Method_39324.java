/** 
 * Returns <code>true</code> if bean has name defined by Petite annotation.
 */
public boolean beanHasAnnotationName(final Class type){
  PetiteBean petiteBean=((Class<?>)type).getAnnotation(PetiteBean.class);
  if (petiteBean == null) {
    return false;
  }
  String name=petiteBean.value().trim();
  return !name.isEmpty();
}
