private void init(JSONObject json) throws TwitterException {
  id=ParseUtil.getLong("id",json);
  source=ParseUtil.getUnescapedString("source",json);
  createdAt=getDate("created_at",json);
  isTruncated=ParseUtil.getBoolean("truncated",json);
  inReplyToStatusId=ParseUtil.getLong("in_reply_to_status_id",json);
  inReplyToUserId=ParseUtil.getLong("in_reply_to_user_id",json);
  isFavorited=ParseUtil.getBoolean("favorited",json);
  isRetweeted=ParseUtil.getBoolean("retweeted",json);
  inReplyToScreenName=ParseUtil.getUnescapedString("in_reply_to_screen_name",json);
  retweetCount=ParseUtil.getLong("retweet_count",json);
  favoriteCount=ParseUtil.getInt("favorite_count",json);
  isPossiblySensitive=ParseUtil.getBoolean("possibly_sensitive",json);
  try {
    if (!json.isNull("user")) {
      user=new UserJSONImpl(json.getJSONObject("user"));
    }
    geoLocation=JSONImplFactory.createGeoLocation(json);
    if (!json.isNull("place")) {
      place=new PlaceJSONImpl(json.getJSONObject("place"));
    }
    if (!json.isNull("retweeted_status")) {
      retweetedStatus=new StatusJSONImpl(json.getJSONObject("retweeted_status"));
    }
    if (!json.isNull("contributors")) {
      JSONArray contributorsArray=json.getJSONArray("contributors");
      contributorsIDs=new long[contributorsArray.length()];
      for (int i=0; i < contributorsArray.length(); i++) {
        contributorsIDs[i]=Long.parseLong(contributorsArray.getString(i));
      }
    }
 else {
      contributorsIDs=new long[0];
    }
    collectEntities(json);
    mergeExtendedEntities(json);
    if (!json.isNull("quoted_status")) {
      quotedStatus=new StatusJSONImpl(json.getJSONObject("quoted_status"));
    }
    if (!json.isNull("quoted_status_id")) {
      quotedStatusId=ParseUtil.getLong("quoted_status_id",json);
    }
    if (!json.isNull("display_text_range")) {
      JSONArray indicesArray=json.getJSONArray("display_text_range");
      displayTextRangeStart=indicesArray.getInt(0);
      displayTextRangeEnd=indicesArray.getInt(1);
    }
    userMentionEntities=userMentionEntities == null ? new UserMentionEntity[0] : userMentionEntities;
    urlEntities=urlEntities == null ? new URLEntity[0] : urlEntities;
    hashtagEntities=hashtagEntities == null ? new HashtagEntity[0] : hashtagEntities;
    symbolEntities=symbolEntities == null ? new SymbolEntity[0] : symbolEntities;
    mediaEntities=mediaEntities == null ? new MediaEntity[0] : mediaEntities;
    if (!json.isNull("text")) {
      text=HTMLEntity.unescapeAndSlideEntityIncdices(json.getString("text"),userMentionEntities,urlEntities,hashtagEntities,mediaEntities);
    }
    if (!json.isNull("full_text")) {
      text=HTMLEntity.unescapeAndSlideEntityIncdices(json.getString("full_text"),userMentionEntities,urlEntities,hashtagEntities,mediaEntities);
    }
    if (!json.isNull("extended_tweet")) {
      mergeExtendedTweet(json.getJSONObject("extended_tweet"));
    }
    if (!json.isNull("current_user_retweet")) {
      currentUserRetweetId=json.getJSONObject("current_user_retweet").getLong("id");
    }
    if (!json.isNull("lang")) {
      lang=ParseUtil.getUnescapedString("lang",json);
    }
    if (!json.isNull("scopes")) {
      JSONObject scopesJson=json.getJSONObject("scopes");
      if (!scopesJson.isNull("place_ids")) {
        JSONArray placeIdsArray=scopesJson.getJSONArray("place_ids");
        int len=placeIdsArray.length();
        String[] placeIds=new String[len];
        for (int i=0; i < len; i++) {
          placeIds[i]=placeIdsArray.getString(i);
        }
        scopes=new ScopesImpl(placeIds);
      }
    }
    if (!json.isNull("withheld_in_countries")) {
      JSONArray withheld_in_countries=json.getJSONArray("withheld_in_countries");
      int length=withheld_in_countries.length();
      withheldInCountries=new String[length];
      for (int i=0; i < length; i++) {
        withheldInCountries[i]=withheld_in_countries.getString(i);
      }
    }
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
