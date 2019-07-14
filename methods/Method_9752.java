/** 
 * Adds a tag.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/add-tag",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void addTag(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  String title=StringUtils.trim(context.param(Tag.TAG_TITLE));
  try {
    if (StringUtils.isBlank(title)) {
      throw new Exception(langPropsService.get("tagsErrorLabel"));
    }
    title=Tag.formatTags(title);
    if (!Tag.containsWhiteListTags(title)) {
      if (!Tag.TAG_TITLE_PATTERN.matcher(title).matches()) {
        throw new Exception(langPropsService.get("tagsErrorLabel"));
      }
      if (title.length() > Tag.MAX_TAG_TITLE_LENGTH) {
        throw new Exception(langPropsService.get("tagsErrorLabel"));
      }
    }
  }
 catch (  final Exception e) {
    final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/error.ftl");
    final Map<String,Object> dataModel=renderer.getDataModel();
    dataModel.put(Keys.MSG,e.getMessage());
    dataModelService.fillHeaderAndFooter(context,dataModel);
    return;
  }
  final JSONObject admin=Sessions.getUser();
  final String userId=admin.optString(Keys.OBJECT_ID);
  String tagId;
  try {
    tagId=tagMgmtService.addTag(userId,title);
    operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_ADD_TAG,title));
  }
 catch (  final ServiceException e) {
    final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/error.ftl");
    final Map<String,Object> dataModel=renderer.getDataModel();
    dataModel.put(Keys.MSG,e.getMessage());
    dataModelService.fillHeaderAndFooter(context,dataModel);
    return;
  }
  context.sendRedirect(Latkes.getServePath() + "/admin/tag/" + tagId);
}
