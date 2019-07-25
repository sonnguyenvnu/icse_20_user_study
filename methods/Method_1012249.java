/** 
 * ????
 * @param pointX x???
 * @param pointY y???
 */
public void show(int pointX,int pointY){
  Window win=getWindow();
  WindowManager.LayoutParams params=win.getAttributes();
  params.x=pointX;
  params.y=pointY;
  win.setAttributes(params);
  show();
}
