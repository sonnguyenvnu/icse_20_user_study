/** 
 * Send the Recycler a request to scroll the content to the first item in the binder.
 * @param animated if animated is set to true the scroll will happen with an animation.
 */
public void requestScrollToTop(boolean animated){
  requestScrollToPosition(0,animated);
}
