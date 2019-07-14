/** 
 * Notifies observers about data changing
 */
protected void notifyDataChangedEvent(){
  if (datasetObservers != null) {
    for (    DataSetObserver observer : datasetObservers) {
      observer.onChanged();
    }
  }
}
