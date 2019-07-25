/** 
 * Called every tick
 */
public void tick(){
  ArrayList<OnlineUser> onlineUsers=new ArrayList<>(onlineUserManager.getOnlineUsers());
  for (  OnlineUser user : onlineUsers) {
    if (user.getWebSocket().isOpen() && user.getUser() != null) {
      JSONObject json=new JSONObject();
      json.put("t","tick");
      if (user.getUser().isGuest()) {
        sendJSONObject(user,json);
      }
 else {
        ControllableUnit unit=user.getUser().getControlledUnit();
        json.put("console_message_buffer",charArraysToJSON(unit.getConsoleMessagesBuffer()));
        json.put("keys",intListToJSON(unit.getKeyboardBuffer()));
        json.put("console_mode",unit.getConsoleMode());
        sendJSONObject(user,json);
      }
    }
  }
}
