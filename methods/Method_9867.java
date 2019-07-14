/** 
 * List usernames.
 * @param context the specified context
 */
@RequestProcessing(value="/users/names",method=HttpMethod.POST) public void listNames(final RequestContext context){
  final JSONObject result=Results.newSucc();
  context.renderJSON(result);
  final JSONObject requestJSON=context.requestJSON();
  final String namePrefix=requestJSON.optString("name");
  if (StringUtils.isBlank(namePrefix)) {
    final List<JSONObject> admins=userQueryService.getAdmins();
    final List<JSONObject> userNames=new ArrayList<>();
    for (    final JSONObject admin : admins) {
      final JSONObject userName=new JSONObject();
      userName.put(User.USER_NAME,admin.optString(User.USER_NAME));
      final String avatar=avatarQueryService.getAvatarURLByUser(admin,"20");
      userName.put(UserExt.USER_AVATAR_URL,avatar);
      userNames.add(userName);
    }
    result.put(Common.DATA,userNames);
    return;
  }
  final List<JSONObject> userNames=userQueryService.getUserNamesByPrefix(namePrefix);
  result.put(Common.DATA,userNames);
}
