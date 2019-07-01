/** 
 * Helper for error callbacks that just returns the Status.ERROR by default
 * @param message           The message to add to the error result.
 */
public void _XXXXX_(int message){
  sendPluginResult(new PluginResult(PluginResult.Status.ERROR,message));
}