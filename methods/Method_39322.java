/** 
 * Resolves bean's scope type from the annotation. Returns <code>null</code> if annotation doesn't exist.
 */
public Class<? extends Scope> resolveBeanScopeType(final Class type){
  PetiteBean petiteBean=((Class<?>)type).getAnnotation(PetiteBean.class);
  return petiteBean != null ? petiteBean.scope() : null;
}
