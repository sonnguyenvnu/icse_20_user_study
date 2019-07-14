private void recoverGame(String userId,String userName,String antiUserId,ChessGame chessGame){
  JSONObject sendText=new JSONObject();
  sendText.put("type",5);
  sendText.put("chess",chessGame.getChess());
  sendText.put("message","???????????????? [" + (chessGame.getStep() == 1 ? chessGame.getName1() : chessGame.getName2()) + "] ??");
  sendText.put("playerName",userName);
  sendText.put("player",userId);
  SESSIONS.get(userId).getAsyncRemote().sendText(sendText.toString());
  sendText=new JSONObject();
  sendText.put("type",6);
  sendText.put("message","??????????????????? [" + (chessGame.getStep() == 1 ? chessGame.getName1() : chessGame.getName2()) + "] ??");
  SESSIONS.get(antiUserId).getAsyncRemote().sendText(sendText.toString());
}
