/** 
 * @return viewType of current {@link RenderInfo} if it was created through {@link ViewRenderInfo#create()} or otherwise it will throw {@link UnsupportedOperationException}. If this method is accessed from  {@link RenderInfo} type, {@link #rendersView()} should bequeried first before accessing.
 */
@Override public int getViewType(){
  return mRenderInfo.getViewType();
}
