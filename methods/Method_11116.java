/** 
 * ????????
 */
public void show(View view){
  view.getLocationOnScreen(mLocation);
  mRect.set(mLocation[0],mLocation[1],mLocation[0] + view.getWidth(),mLocation[1] + view.getHeight());
  if (mIsDirty) {
    populateActions();
  }
  showAtLocation(view,popupGravity,mScreenWidth - LIST_PADDING - (getWidth() / 2),mRect.bottom + RxImageTool.dp2px(7.5f));
}
