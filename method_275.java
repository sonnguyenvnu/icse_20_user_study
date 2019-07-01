/** 
 * Bind all endpoints to corresponding handlers.
 */
public void _XXXXX_(){
  for (  Map.Entry<String,Handler> entry : endpointHandlers.entrySet()) {
    bindHandler(entry.getKey(),entry.getValue());
  }
}