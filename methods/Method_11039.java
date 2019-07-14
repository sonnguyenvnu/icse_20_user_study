/** 
 * ????match_parent
 */
public void setFullScreenWidth(){
  Window window=getWindow();
  window.getDecorView().setPadding(0,0,0,0);
  WindowManager.LayoutParams lp=window.getAttributes();
  lp.width=WindowManager.LayoutParams.FILL_PARENT;
  lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
  window.setAttributes(lp);
}
