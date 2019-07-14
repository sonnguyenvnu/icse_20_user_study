/** 
 * Sets the controller. 
 */
public void setController(@Nullable DraweeController draweeController){
  mDraweeHolder.setController(draweeController);
  super.setImageDrawable(mDraweeHolder.getTopLevelDrawable());
}
