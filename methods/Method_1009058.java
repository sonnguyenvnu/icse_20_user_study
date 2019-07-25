/** 
 * Updates the state from the inserted event.
 * @param e The inserted event.
 */
public void insert(DiffXEvent e){
  if (e instanceof OpenElementEvent) {
    push((OpenElementEvent)e,'+');
  }
 else   if (e instanceof CloseElementEvent) {
    pop();
  }
}
