/** 
 * Notifies listeners about clicking
 * @param item clicked item
 */
protected void notifyClickListenersAboutClick(int item){
  for (  OnWheelClickedListener listener : clickingListeners) {
    listener.onItemClicked(this,item);
  }
}
