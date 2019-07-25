/** 
 * If set, will enable scrolling of the search request for the specified timeout.
 */
public SearchScrollRequest scroll(TimeValue keepAlive){
  return scroll(new Scroll(keepAlive));
}
