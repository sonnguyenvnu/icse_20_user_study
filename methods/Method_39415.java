/** 
 * Returns <code>true</code> if bean is destroyable.
 */
protected boolean isBeanDestroyable(final BeanData beanData){
  DestroyMethodPoint[] dmp=beanData.definition().destroyMethodPoints();
  return dmp != null && dmp.length != 0;
}
