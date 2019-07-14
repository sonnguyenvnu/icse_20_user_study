private void sendBotInlineResult(TLRPC.BotInlineResult result){
  int uid=mentionsAdapter.getContextBotId();
  HashMap<String,String> params=new HashMap<>();
  params.put("id",result.id);
  params.put("query_id","" + result.query_id);
  params.put("bot","" + uid);
  params.put("bot_name",mentionsAdapter.getContextBotName());
  SendMessagesHelper.prepareSendingBotContextResult(result,params,dialog_id,replyingMessageObject);
  chatActivityEnterView.setFieldText("");
  hideFieldPanel(false);
  DataQuery.getInstance(currentAccount).increaseInlineRaiting(uid);
}
