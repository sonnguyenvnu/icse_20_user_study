/** 
 * Whenever a Spec sets tree props, the TreeProps map from the parent is copied. If parent TreeProps are null, a new TreeProps instance is created to copy the current tree props. <p>Infer knows that newProps is owned but doesn't know that newProps.mMap is owned.
 */
@ThreadSafe(enableChecks=false) public static TreeProps acquire(TreeProps source){
  final TreeProps newProps=new TreeProps();
  if (source != null) {
synchronized (source.mMap) {
      newProps.mMap.putAll(source.mMap);
    }
  }
  return newProps;
}
