/** 
 * @return true, if {@link RenderInfo} was created through {@link ViewRenderInfo#create()}, or false otherwise. This should be queried before accessing view related methods, such as {@link #getViewBinder()},  {@link #getViewCreator()},  {@link #getViewType()} and {@link #setViewType(int)} from {@link RenderInfo} type.
 */
@Override public boolean rendersView(){
  return mRenderInfo.rendersView();
}
