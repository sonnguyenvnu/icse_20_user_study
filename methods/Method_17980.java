/** 
 * @return the {@link LithoView} associated with this ComponentTree if any.
 */
@Keep @Nullable public LithoView getLithoView(){
  assertMainThread();
  return mLithoView;
}
