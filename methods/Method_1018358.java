/** 
 * Returns whether the  {@link ETag} matches the given {@link PersistentEntity} and target. A more dissenting way ofchecking matches as it does not match if the ETag is  {@link #NO_ETAG}.
 * @param entity must not be {@literal null}.
 * @param target can be {@literal null}.
 * @return
 */
public boolean matches(PersistentEntity<?,?> entity,Object target){
  if (this == NO_ETAG || target == null) {
    return false;
  }
  return this.equals(from(entity,target));
}
