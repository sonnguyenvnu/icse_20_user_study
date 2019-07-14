/** 
 * ????????
 */
public void show(View view,int dex){
  view.getLocationOnScreen(mLocation);
  mRect.set(mLocation[0],mLocation[1],mLocation[0] + view.getWidth(),mLocation[1] + view.getHeight());
  if (mIsDirty) {
    populateActions();
  }
  showAtLocation(view,popupGravity,mLocation[0],mRect.bottom + dex);
}
