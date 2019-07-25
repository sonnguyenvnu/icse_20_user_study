/** 
 * Initializes all variables. Used after close() or disconnect(), to be ready for new connect() 
 */
protected JChannel init(){
  if (local_addr != null)   down(new Event(Event.REMOVE_ADDRESS,local_addr));
  local_addr=null;
  cluster_name=null;
  view=null;
  return this;
}
