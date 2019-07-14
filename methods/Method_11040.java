/** 
 * ?????match_parent
 */
public void setFullScreenHeight(){
  Window window=getWindow();
  window.getDecorView().setPadding(0,0,0,0);
  WindowManager.LayoutParams lp=window.getAttributes();
  lp.width=WindowManager.LayoutParams.WRAP_CONTENT;
  lp.height=WindowManager.LayoutParams.FILL_PARENT;
  window.setAttributes(lp);
}
