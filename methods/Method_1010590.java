/** 
 * Gives access to events collected so far and clears all collected events. Doesn't change start/stop state.
 * @return ordered collection of model change events, or empty list if none had happened.
 */
@NotNull public List<AbstractModelChangeEvent> purge(){
  ArrayList<AbstractModelChangeEvent> rv=new ArrayList<>(myEvents);
  myEvents.clear();
  return rv;
}
