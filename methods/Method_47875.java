/** 
 * Notifies every listener that the model has changed. <p> Only models should call this method.
 */
public synchronized void notifyListeners(){
  for (  Listener l : listeners)   l.onModelChange();
}
