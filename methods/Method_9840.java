/** 
 * Shows settings pages.
 * @param context the specified context
 */
@RequestProcessing(value={"/settings","/settings/{page}"},method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,LoginCheck.class}) @After({CSRFToken.class,PermissionGrant.class,StopwatchEndAdvice.class}) public void showSettings(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,null);
  context.setRenderer(renderer);
  String page=context.pathVar("page");
  if (StringUtils.isBlank(page)) {
    page="profile";
  }
  page+=".ftl";
  renderer.setTemplateName("home/settings/" + page);
  final Map<String,Object> dataModel=renderer.getDataModel();
  final JSONObject user=Sessions.getUser();
  UserProcessor.fillHomeUser(dataModel,user,roleQueryService);
  avatarQueryService.fillUserAvatarURL(user);
  final String userId=user.optString(Keys.OBJECT_ID);
  final int invitedUserCount=userQueryService.getInvitedUserCount(userId);
  dataModel.put(Common.INVITED_USER_COUNT,invitedUserCount);
  final long imgMaxSize=Symphonys.UPLOAD_IMG_MAX;
  dataModel.put("imgMaxSize",imgMaxSize);
  final long fileMaxSize=Symphonys.UPLOAD_FILE_MAX;
  dataModel.put("fileMaxSize",fileMaxSize);
  dataModelService.fillHeaderAndFooter(context,dataModel);
  String inviteTipLabel=(String)dataModel.get("inviteTipLabel");
  inviteTipLabel=inviteTipLabel.replace("{point}",String.valueOf(Pointtransfer.TRANSFER_SUM_C_INVITE_REGISTER));
  dataModel.put("inviteTipLabel",inviteTipLabel);
  String pointTransferTipLabel=(String)dataModel.get("pointTransferTipLabel");
  pointTransferTipLabel=pointTransferTipLabel.replace("{point}",Symphonys.POINT_TRANSER_MIN + "");
  dataModel.put("pointTransferTipLabel",pointTransferTipLabel);
  String dataExportTipLabel=(String)dataModel.get("dataExportTipLabel");
  dataExportTipLabel=dataExportTipLabel.replace("{point}",String.valueOf(Pointtransfer.TRANSFER_SUM_C_DATA_EXPORT));
  dataModel.put("dataExportTipLabel",dataExportTipLabel);
  final String allowRegister=optionQueryService.getAllowRegister();
  dataModel.put("allowRegister",allowRegister);
  String buyInvitecodeLabel=langPropsService.get("buyInvitecodeLabel");
  buyInvitecodeLabel=buyInvitecodeLabel.replace("${point}",String.valueOf(Pointtransfer.TRANSFER_SUM_C_BUY_INVITECODE));
  buyInvitecodeLabel=buyInvitecodeLabel.replace("${point2}",String.valueOf(Pointtransfer.TRANSFER_SUM_C_INVITECODE_USED));
  dataModel.put("buyInvitecodeLabel",buyInvitecodeLabel);
  String updateNameTipLabel=(String)dataModel.get("updateNameTipLabel");
  updateNameTipLabel=updateNameTipLabel.replace("{point}",Symphonys.POINT_CHANGE_USERNAME + "");
  dataModel.put("updateNameTipLabel",updateNameTipLabel);
  final List<JSONObject> invitecodes=invitecodeQueryService.getValidInvitecodes(userId);
  for (  final JSONObject invitecode : invitecodes) {
    String msg=langPropsService.get("expireTipLabel");
    msg=msg.replace("${time}",DateFormatUtils.format(invitecode.optLong(Keys.OBJECT_ID) + Symphonys.INVITECODE_EXPIRED,"yyyy-MM-dd HH:mm"));
    invitecode.put(Common.MEMO,msg);
  }
  dataModel.put(Invitecode.INVITECODES,invitecodes);
  final String requestURI=context.requestURI();
  if (requestURI.contains("function")) {
    dataModel.put(Emotion.EMOTIONS,emotionQueryService.getEmojis(userId));
    dataModel.put(Emotion.SHORT_T_LIST,emojiLists);
  }
  if (requestURI.contains("i18n")) {
    dataModel.put(Common.LANGUAGES,Languages.getAvailableLanguages());
    final List<JSONObject> timezones=new ArrayList<>();
    final List<TimeZones.TimeZoneWithDisplayNames> timeZones=TimeZones.getInstance().getTimeZones();
    for (    final TimeZones.TimeZoneWithDisplayNames timeZone : timeZones) {
      final JSONObject timezone=new JSONObject();
      timezone.put(Common.ID,timeZone.getTimeZone().getID());
      timezone.put(Common.NAME,timeZone.getDisplayName());
      timezones.add(timezone);
    }
    dataModel.put(Common.TIMEZONES,timezones);
  }
  dataModel.put(Common.TYPE,"settings");
  notificationMgmtService.makeRead(userId,Notification.DATA_TYPE_C_SYS_ANNOUNCE_NEW_USER);
}
