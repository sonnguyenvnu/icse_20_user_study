private void init(JSONObject json) throws TwitterException {
  try {
    id=ParseUtil.getLong("id",json);
    name=ParseUtil.getRawString("name",json);
    email=ParseUtil.getRawString("email",json);
    screenName=ParseUtil.getRawString("screen_name",json);
    location=ParseUtil.getRawString("location",json);
    descriptionURLEntities=getURLEntitiesFromJSON(json,"description");
    URLEntity[] urlEntities=getURLEntitiesFromJSON(json,"url");
    if (urlEntities.length > 0) {
      urlEntity=urlEntities[0];
    }
    description=ParseUtil.getRawString("description",json);
    if (description != null) {
      description=HTMLEntity.unescapeAndSlideEntityIncdices(description,null,descriptionURLEntities,null,null);
    }
    isContributorsEnabled=ParseUtil.getBoolean("contributors_enabled",json);
    profileImageUrl=ParseUtil.getRawString("profile_image_url",json);
    profileImageUrlHttps=ParseUtil.getRawString("profile_image_url_https",json);
    isDefaultProfileImage=ParseUtil.getBoolean("default_profile_image",json);
    url=ParseUtil.getRawString("url",json);
    isProtected=ParseUtil.getBoolean("protected",json);
    isGeoEnabled=ParseUtil.getBoolean("geo_enabled",json);
    isVerified=ParseUtil.getBoolean("verified",json);
    translator=ParseUtil.getBoolean("is_translator",json);
    followersCount=ParseUtil.getInt("followers_count",json);
    profileBackgroundColor=ParseUtil.getRawString("profile_background_color",json);
    profileTextColor=ParseUtil.getRawString("profile_text_color",json);
    profileLinkColor=ParseUtil.getRawString("profile_link_color",json);
    profileSidebarFillColor=ParseUtil.getRawString("profile_sidebar_fill_color",json);
    profileSidebarBorderColor=ParseUtil.getRawString("profile_sidebar_border_color",json);
    profileUseBackgroundImage=ParseUtil.getBoolean("profile_use_background_image",json);
    isDefaultProfile=ParseUtil.getBoolean("default_profile",json);
    showAllInlineMedia=ParseUtil.getBoolean("show_all_inline_media",json);
    friendsCount=ParseUtil.getInt("friends_count",json);
    createdAt=ParseUtil.getDate("created_at",json,"EEE MMM dd HH:mm:ss z yyyy");
    favouritesCount=ParseUtil.getInt("favourites_count",json);
    utcOffset=ParseUtil.getInt("utc_offset",json);
    timeZone=ParseUtil.getRawString("time_zone",json);
    profileBackgroundImageUrl=ParseUtil.getRawString("profile_background_image_url",json);
    profileBackgroundImageUrlHttps=ParseUtil.getRawString("profile_background_image_url_https",json);
    profileBannerImageUrl=ParseUtil.getRawString("profile_banner_url",json);
    profileBackgroundTiled=ParseUtil.getBoolean("profile_background_tile",json);
    lang=ParseUtil.getRawString("lang",json);
    statusesCount=ParseUtil.getInt("statuses_count",json);
    listedCount=ParseUtil.getInt("listed_count",json);
    isFollowRequestSent=ParseUtil.getBoolean("follow_request_sent",json);
    if (!json.isNull("status")) {
      JSONObject statusJSON=json.getJSONObject("status");
      status=new StatusJSONImpl(statusJSON);
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
    throw new TwitterException(jsone.getMessage() + ":" + json.toString(),jsone);
  }
}
