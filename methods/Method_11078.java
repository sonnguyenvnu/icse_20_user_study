/** 
 * Finishes scrolling
 */
void finishScrolling(){
  if (isScrollingPerformed) {
    listener.onFinished();
    isScrollingPerformed=false;
  }
}
