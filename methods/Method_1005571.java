/** 
 * ?containerViewId??????Fragment
 * @param containerViewId ??ID
 * @return this
 */
public AbsFragmentTransactionUriRequest replace(@IdRes int containerViewId){
  mContainerViewId=containerViewId;
  mType=TYPE_REPLACE;
  return this;
}
