/** 
 * Detach an observer from this object.
 * @param observer The observer object to be detached from.
 */
public void detach(Observer observer){
  if (mIterator != null) {
    mIterator.remove();
  }
 else {
    mObservers.remove(observer);
  }
}
