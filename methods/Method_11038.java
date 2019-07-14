/** 
 * ??????
 */
public void setFullScreen(){
  Window window=getWindow();
  window.getDecorView().setPadding(0,0,0,0);
  LayoutParams lp=window.getAttributes();
  lp.width=WindowManager.LayoutParams.FILL_PARENT;
  lp.height=WindowManager.LayoutParams.FILL_PARENT;
  window.setAttributes(lp);
}
