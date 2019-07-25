/** 
 * Send back a response to the command issuer
 */
public void reply(String message){
  JSONObject response=new JSONObject();
  response.put("t","debug");
  response.put("message",message);
  try {
    ((OnlineUser)getSource()).getWebSocket().getRemote().sendString(response.toJSONString());
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
