/** 
 * List frequent emotions.
 * @param context the specified context
 */
@RequestProcessing(value="/users/emotions",method=HttpMethod.GET) public void getFrequentEmotions(final RequestContext context){
  final JSONObject result=Results.newSucc();
  context.renderJSON(result);
  final List<JSONObject> data=new ArrayList<>();
  final JSONObject currentUser=Sessions.getUser();
  if (null == currentUser) {
    result.put(Common.DATA,data);
    return;
  }
  final String userId=currentUser.optString(Keys.OBJECT_ID);
  String emotions=emotionQueryService.getEmojis(userId);
  final String[] emojis=emotions.split(",");
  for (  final String emoji : emojis) {
    String emojiChar=Emotions.toUnicode(":" + emoji + ":");
    if (StringUtils.contains(emojiChar,":")) {
      final String suffix="huaji".equals(emoji) ? ".gif" : ".png";
      emojiChar=Latkes.getStaticServePath() + "/emoji/graphics/" + emoji + suffix;
    }
    data.add(new JSONObject().put(emoji,emojiChar));
  }
  result.put(Common.DATA,data);
}
