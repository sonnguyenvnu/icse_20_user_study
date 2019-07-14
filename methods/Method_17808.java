/** 
 * Generate a global key for the given component that is unique among all of this component's children of the same type. If a manual key has been set on the child component using the .key() method, return the manual key. <p>TODO: (T38237241) remove the usage of the key handler post the nested tree experiment
 * @param component the child component for which we're finding a unique global key
 * @param key the key of the child component as determined by its lifecycle id or manual setting
 * @return a unique global key for this component relative to its siblings.
 */
private String generateUniqueGlobalKeyForChild(Component component,String key){
  final String childKey=ComponentKeyUtils.getKeyWithSeparator(getGlobalKey(),key);
  final KeyHandler keyHandler=mScopedContext.getKeyHandler();
  if (keyHandler == null) {
    return childKey;
  }
  if (mScopedContext.isNestedTreeResolutionExperimentEnabled()) {
    if (component.mHasManualKey) {
      if (mManualKeys == null) {
        mManualKeys=new HashSet<>();
      }
      if (mManualKeys.contains(childKey)) {
        logDuplicateManualKeyWarning(component,key);
      }
 else {
        mManualKeys.add(childKey);
        getChildCountAndIncrement(component);
        return childKey;
      }
    }
    int childCount=getChildCountAndIncrement(component);
    if (childCount == 0) {
      return childKey;
    }
 else {
      return getKeyForChildPosition(childKey,childCount);
    }
  }
 else   if (keyHandler.hasKey(childKey)) {
    if (component.mHasManualKey) {
      logDuplicateManualKeyWarning(component,key);
    }
    return getKeyForChildPosition(childKey,getChildCountAndIncrement(component));
  }
 else {
    return childKey;
  }
}
