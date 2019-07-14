/** 
 * Returns whether an event with given schemeIdUri and value is a DASH emsg event targeting the player.
 */
public static boolean isPlayerEmsgEvent(String schemeIdUri,String value){
  return "urn:mpeg:dash:event:2012".equals(schemeIdUri) && ("1".equals(value) || "2".equals(value) || "3".equals(value));
}
