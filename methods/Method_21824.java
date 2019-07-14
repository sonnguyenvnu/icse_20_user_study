/** 
 * Sets the visibility  {@link #topbar}, which contains the previous month button  {@link #buttonPast}, next month button  {@link #buttonFuture}, and the month title  {@link #title}.
 * @param visible Boolean indicating if the topbar is visible
 */
public void setTopbarVisible(boolean visible){
  topbar.setVisibility(visible ? View.VISIBLE : View.GONE);
  requestLayout();
}
