/** 
 * Take the working copy state and commit it back to the original target. The target then reflects all the changes applied to the copy during a transaction.
 * @param copy the working copy.
 * @param target the original target of the factory.
 */
@SuppressWarnings({"unchecked","rawtypes"}) protected void commit(T copy,T target){
synchronized (target) {
    if (target instanceof Collection) {
      if (!appendOnly) {
        ((Collection)target).clear();
      }
      ((Collection)target).addAll((Collection)copy);
    }
 else {
      if (!appendOnly) {
        ((Map)target).clear();
      }
      ((Map)target).putAll((Map)copy);
    }
  }
}
