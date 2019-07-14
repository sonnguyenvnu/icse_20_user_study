/** 
 * Resolves bean's auto-wire flag from the annotation. Returns default auto-wire if annotation doesn't exist.
 */
public WiringMode resolveBeanWiringMode(final Class type){
  PetiteBean petiteBean=((Class<?>)type).getAnnotation(PetiteBean.class);
  return petiteBean != null ? petiteBean.wiring() : WiringMode.DEFAULT;
}
