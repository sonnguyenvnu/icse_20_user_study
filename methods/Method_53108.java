private void collectEntities(JSONObject json) throws JSONException, TwitterException {
  if (!json.isNull("entities")) {
    JSONObject entities=json.getJSONObject("entities");
    userMentionEntities=EntitiesParseUtil.getUserMentions(entities);
    urlEntities=EntitiesParseUtil.getUrls(entities);
    hashtagEntities=EntitiesParseUtil.getHashtags(entities);
    symbolEntities=EntitiesParseUtil.getSymbols(entities);
    mediaEntities=EntitiesParseUtil.getMedia(entities);
  }
  if (!json.isNull("quoted_status")) {
    quotedStatus=new StatusJSONImpl(json.getJSONObject("quoted_status"));
  }
  if (!json.isNull("quoted_status_id")) {
    quotedStatusId=ParseUtil.getLong("quoted_status_id",json);
  }
  if (!json.isNull("quoted_status_permalink")) {
    quotedStatusPermalink=new QuotedStatusPermalinkJSONImpl(json.getJSONObject("quoted_status_permalink"));
  }
}
