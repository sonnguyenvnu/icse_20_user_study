/** 
 * Updates the state from the formatted event.
 * @param e The formatted event.
 */
public void format(DiffXEvent e){
  if (e instanceof OpenElementEvent) {
    push((OpenElementEvent)e,'=');
  }
 else   if (e instanceof CloseElementEvent) {
    pop();
  }
}
