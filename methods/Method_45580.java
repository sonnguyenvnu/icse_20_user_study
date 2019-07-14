/** 
 * ?????
 * @param destroyHook ??
 */
public static void registryDestroyHook(Destroyable.DestroyHook destroyHook){
  DESTROY_HOOKS.add(destroyHook);
}
