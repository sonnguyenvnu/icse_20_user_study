/** 
 * Updates the state from the deleted event.
 * @param e The deleted event.
 */
public void delete(DiffXEvent e){
  if (e instanceof OpenElementEvent) {
    push((OpenElementEvent)e,'-');
  }
 else   if (e instanceof CloseElementEvent) {
    pop();
  }
}
