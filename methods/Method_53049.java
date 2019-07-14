private void init(JSONObject json) throws TwitterException {
  try {
    id=ParseUtil.getLong("id",json);
    JSONObject messageCreate;
    final JSONObject messageData;
    if (!json.isNull("created_timestamp")) {
      createdAt=new Date(json.getLong("created_timestamp"));
      messageCreate=json.getJSONObject("message_create");
      recipientId=ParseUtil.getLong("recipient_id",messageCreate.getJSONObject("target"));
      senderId=ParseUtil.getLong("sender_id",messageCreate);
      messageData=messageCreate.getJSONObject("message_data");
    }
 else {
      createdAt=ParseUtil.getDate("created_at",json);
      ;
      senderId=ParseUtil.getLong("sender_id",json);
      recipientId=ParseUtil.getLong("recipient_id",json);
      messageData=json;
    }
    if (!messageData.isNull("entities")) {
      JSONObject entities=messageData.getJSONObject("entities");
      userMentionEntities=EntitiesParseUtil.getUserMentions(entities);
      urlEntities=EntitiesParseUtil.getUrls(entities);
      hashtagEntities=EntitiesParseUtil.getHashtags(entities);
      symbolEntities=EntitiesParseUtil.getSymbols(entities);
    }
    userMentionEntities=userMentionEntities == null ? new UserMentionEntity[0] : userMentionEntities;
    urlEntities=urlEntities == null ? new URLEntity[0] : urlEntities;
    hashtagEntities=hashtagEntities == null ? new HashtagEntity[0] : hashtagEntities;
    symbolEntities=symbolEntities == null ? new SymbolEntity[0] : symbolEntities;
    if (!messageData.isNull("attachment")) {
      JSONObject attachment=messageData.getJSONObject("attachment");
      if (!attachment.isNull("media")) {
        mediaEntities=new MediaEntity[1];
        mediaEntities[0]=new MediaEntityJSONImpl(attachment.getJSONObject("media"));
      }
    }
    if (!messageData.isNull("quick_reply")) {
      JSONArray options=messageData.getJSONObject("quick_reply").getJSONArray("options");
      List<QuickReply> quickReplyList=new ArrayList<QuickReply>();
      for (int i=0; i < options.length(); i++) {
        JSONObject option=options.getJSONObject(i);
        String description=option.isNull("description") ? null : option.getString("description");
        String metadata=option.isNull("metadata") ? null : option.getString("metadata");
        quickReplyList.add(new QuickReply(option.getString("label"),description,metadata));
      }
      quickReplies=quickReplyList.toArray(new QuickReply[quickReplyList.size()]);
    }
 else {
      quickReplies=new QuickReply[0];
    }
    if (!messageData.isNull("quick_reply_response") && !messageData.getJSONObject("quick_reply_response").isNull("metadata")) {
      quickReplyResponse=messageData.getJSONObject("quick_reply_response").getString("metadata");
    }
    mediaEntities=mediaEntities == null ? new MediaEntity[0] : mediaEntities;
    text=HTMLEntity.unescapeAndSlideEntityIncdices(messageData.getString("text"),userMentionEntities,urlEntities,hashtagEntities,mediaEntities);
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
