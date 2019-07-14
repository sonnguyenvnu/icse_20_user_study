private static JSONObject createMessageCreateJsonObject(long recipientId,String text,long mediaId,String quickReplyResponse,QuickReply... quickReplies) throws JSONException {
  String type=mediaId == -1 ? null : "media";
  final JSONObject messageDataJSON=new JSONObject();
  final JSONObject target=new JSONObject();
  target.put("recipient_id",recipientId);
  messageDataJSON.put("target",target);
  final JSONObject messageData=new JSONObject();
  messageData.put("text",text);
  if (type != null && mediaId != -1) {
    final JSONObject attachment=new JSONObject();
    attachment.put("type",type);
    if (type.equals("media")) {
      final JSONObject media=new JSONObject();
      media.put("id",mediaId);
      attachment.put("media",media);
    }
    messageData.put("attachment",attachment);
  }
  if (quickReplies.length > 0) {
    JSONObject quickReplyJSON=new JSONObject();
    quickReplyJSON.put("type","options");
    JSONArray jsonArray=new JSONArray();
    for (    QuickReply quickReply : quickReplies) {
      JSONObject option=new JSONObject();
      option.put("label",quickReply.getLabel());
      if (quickReply.getDescription() != null) {
        option.put("description",quickReply.getDescription());
      }
      if (quickReply.getMetadata() != null) {
        option.put("metadata",quickReply.getMetadata());
      }
      jsonArray.put(option);
    }
    quickReplyJSON.put("options",jsonArray);
    messageData.put("quick_reply",quickReplyJSON);
  }
  if (quickReplyResponse != null) {
    JSONObject quickReplyResponseJSON=new JSONObject();
    quickReplyResponseJSON.put("type","options");
    quickReplyResponseJSON.put("metadata",quickReplyResponse);
    messageData.put("quick_reply_response",quickReplyResponseJSON);
  }
  messageDataJSON.put("message_data",messageData);
  final JSONObject json=new JSONObject();
  final JSONObject event=new JSONObject();
  event.put("type","message_create");
  event.put("message_create",messageDataJSON);
  json.put("event",event);
  return json;
}
