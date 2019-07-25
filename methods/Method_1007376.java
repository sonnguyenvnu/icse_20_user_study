/** 
 * Soften a class
 * @param clazz the class
 */
public synchronized void soften(CtClass clazz){
  if (repository.isPrune())   clazz.prune();
  classes.remove(clazz.getName());
  softcache.put(clazz.getName(),clazz);
}
