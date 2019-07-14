/** 
 * Called when the socket connection with the browser is established.
 * @param session session
 */
@OnOpen public void onConnect(final Session session){
  final JSONObject user=(JSONObject)Channels.getHttpSessionAttribute(session,User.USER);
  if (null == user) {
    return;
  }
  final String userId=user.optString(Keys.OBJECT_ID);
  final String userName=user.optString(User.USER_NAME);
  boolean playing=false;
  LOGGER.debug("new connection from " + userName);
  if (SESSIONS.containsKey(userId)) {
    JSONObject sendText=new JSONObject();
    sendText.put("type",6);
    sendText.put("message","??????????????????????????????????????????");
    session.getAsyncRemote().sendText(sendText.toString());
    return;
  }
 else {
    SESSIONS.put(userId,session);
  }
  for (  String temp : chessPlaying.keySet()) {
    ChessGame chessGame=chessPlaying.get(temp);
    if (userId.equals(chessGame.getPlayer1())) {
      recoverGame(userId,userName,chessGame.getPlayer2(),chessGame);
      chessGame.setPlayState1(true);
      playing=true;
    }
 else     if (userId.equals(chessGame.getPlayer2())) {
      recoverGame(userId,userName,chessGame.getPlayer1(),chessGame);
      chessGame.setPlayState2(true);
      playing=true;
    }
  }
  if (playing) {
    return;
  }
 else {
    ChessGame chessGame=null;
    JSONObject sendText=new JSONObject();
    do {
      chessGame=chessRandomWait.poll();
    }
 while (chessRandomWait.size() > 0 && SESSIONS.get(chessGame.getPlayer1()) == null);
    if (chessGame == null) {
      chessGame=new ChessGame(userId,userName);
      chessRandomWait.add(chessGame);
      sendText.put("type",3);
      sendText.put("playerName",userName);
      sendText.put("message","?????????????????");
      session.getAsyncRemote().sendText(sendText.toString());
    }
 else     if (userId.equals(chessGame.getPlayer1())) {
      chessRandomWait.add(chessGame);
      sendText.put("type",3);
      sendText.put("playerName",userName);
      sendText.put("message","?????????????????");
      session.getAsyncRemote().sendText(sendText.toString());
    }
 else {
      final BeanManager beanManager=BeanManager.getInstance();
      chessGame.setPlayer2(userId);
      chessGame.setName2(userName);
      chessGame.setPlayState2(true);
      chessGame.setStep(1);
      chessPlaying.put(chessGame.getPlayer1(),chessGame);
      antiPlayer.put(chessGame.getPlayer1(),chessGame.getPlayer2());
      final ActivityMgmtService activityMgmtService=beanManager.getReference(ActivityMgmtService.class);
      sendText.put("type",4);
      sendText.put("message","??????? [" + userName + "] ????????????");
      sendText.put("player",chessGame.getPlayer1());
      SESSIONS.get(chessGame.getPlayer1()).getAsyncRemote().sendText(sendText.toString());
      sendText.put("message","????~????? [" + chessGame.getName1() + "] ??");
      sendText.put("player",chessGame.getPlayer2());
      session.getAsyncRemote().sendText(sendText.toString());
      JSONObject r1=activityMgmtService.startGobang(chessGame.getPlayer1());
      JSONObject r2=activityMgmtService.startGobang(chessGame.getPlayer2());
    }
  }
}
