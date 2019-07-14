/** 
 * @return the MountContentPool that should be used to recycle mount content for this mount spec.
 */
protected MountContentPool onCreateMountContentPool(){
  return new DefaultMountContentPool(getClass().getSimpleName(),poolSize(),true);
}
