/** 
 * Should be called when this instance is no longer needed.
 */
public void dispose(){
  ParseResultBroadcaster.getParseResultBroadcaster().removeParseResultListener(this);
}
