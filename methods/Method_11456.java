/** 
 * Finishes scrolling
 */
protected void finishScrolling(){
  if (isScrollingPerformed) {
    listener.onFinished();
    isScrollingPerformed=false;
  }
}
