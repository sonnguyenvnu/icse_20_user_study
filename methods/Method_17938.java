/** 
 * Pre-allocates mount content for this component type within the pool for this context unless the pre-allocation limit has been hit in which case we do nothing.
 */
public static void maybePreallocateContent(Context context,ComponentLifecycle lifecycle){
  final MountContentPool pool=getMountContentPool(context,lifecycle);
  if (pool != null) {
    pool.maybePreallocateContent(context,lifecycle);
  }
}
