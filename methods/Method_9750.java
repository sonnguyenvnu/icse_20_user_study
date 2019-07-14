/** 
 * Shows ad.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/ad",method=HttpMethod.GET) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void showAd(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"admin/ad.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  dataModel.put("sideFullAd","");
  dataModel.put("headerBanner","");
  final JSONObject sideAdOption=optionQueryService.getOption(Option.ID_C_SIDE_FULL_AD);
  if (null != sideAdOption) {
    dataModel.put("sideFullAd",sideAdOption.optString(Option.OPTION_VALUE));
  }
  final JSONObject headerBanner=optionQueryService.getOption(Option.ID_C_HEADER_BANNER);
  if (null != headerBanner) {
    dataModel.put("headerBanner",headerBanner.optString(Option.OPTION_VALUE));
  }
  dataModelService.fillHeaderAndFooter(context,dataModel);
}
