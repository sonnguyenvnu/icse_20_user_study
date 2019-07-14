/** 
 * Go to next month or week without using the button  {@link #buttonFuture}. Should only go to next if  {@link #canGoForward()} is enabled, meaning it's possible to go to the next month orweek.
 */
public void goToNext(){
  if (canGoForward()) {
    pager.setCurrentItem(pager.getCurrentItem() + 1,true);
  }
}
