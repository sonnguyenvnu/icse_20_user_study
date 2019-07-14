/** 
 * Removes the specified session.
 * @param session the specified session
 */
private void removeSession(final Session session){
  for (  String player : SESSIONS.keySet()) {
    if (session.equals(SESSIONS.get(player))) {
      if (getAntiPlayer(player) == null) {
        for (        ChessGame chessGame : chessRandomWait) {
          if (player.equals(chessGame.getPlayer1())) {
            chessRandomWait.remove(chessGame);
          }
        }
      }
 else {
        if (chessPlaying.get(player) != null) {
          ChessGame chessGame=chessPlaying.get(player);
          chessGame.setPlayState1(false);
          if (!chessGame.isPlayState2()) {
            chessPlaying.remove(player);
            antiPlayer.remove(player);
            final BeanManager beanManager=BeanManager.getInstance();
            final ActivityMgmtService activityMgmtService=beanManager.getReference(ActivityMgmtService.class);
            activityMgmtService.collectGobang(chessGame.getPlayer1(),Pointtransfer.TRANSFER_SUM_C_ACTIVITY_GOBANG_START);
          }
 else {
            JSONObject sendText=new JSONObject();
            sendText.put("type",6);
            sendText.put("message","????????????");
            SESSIONS.get(chessGame.getPlayer2()).getAsyncRemote().sendText(sendText.toString());
          }
        }
 else         if (chessPlaying.get(getAntiPlayer(player)) != null) {
          String player1=getAntiPlayer(player);
          ChessGame chessGame=chessPlaying.get(player1);
          chessGame.setPlayState2(false);
          if (!chessGame.isPlayState1()) {
            chessPlaying.remove(player1);
            antiPlayer.remove(player1);
            final BeanManager beanManager=BeanManager.getInstance();
            final ActivityMgmtService activityMgmtService=beanManager.getReference(ActivityMgmtService.class);
            activityMgmtService.collectGobang(chessGame.getPlayer2(),Pointtransfer.TRANSFER_SUM_C_ACTIVITY_GOBANG_START);
          }
 else {
            JSONObject sendText=new JSONObject();
            sendText.put("type",6);
            sendText.put("message","????????????");
            SESSIONS.get(chessGame.getPlayer1()).getAsyncRemote().sendText(sendText.toString());
          }
        }
      }
      SESSIONS.remove(player);
    }
  }
}
