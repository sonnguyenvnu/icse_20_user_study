/** 
 * Updates banner.
 * @param context the specified context
 */
@RequestProcessing(value="/admin/ad/banner",method=HttpMethod.POST) @Before({StopwatchStartAdvice.class,PermissionCheck.class}) @After({PermissionGrant.class,StopwatchEndAdvice.class}) public void updateBanner(final RequestContext context){
  final HttpServletRequest request=context.getRequest();
  final String headerBanner=context.param("headerBanner");
  JSONObject adOption=optionQueryService.getOption(Option.ID_C_HEADER_BANNER);
  if (null == adOption) {
    adOption=new JSONObject();
    adOption.put(Keys.OBJECT_ID,Option.ID_C_HEADER_BANNER);
    adOption.put(Option.OPTION_CATEGORY,Option.CATEGORY_C_AD);
    adOption.put(Option.OPTION_VALUE,headerBanner);
    optionMgmtService.addOption(adOption);
    operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_ADD_AD_POS,Option.ID_C_HEADER_BANNER));
  }
 else {
    adOption.put(Option.OPTION_VALUE,headerBanner);
    optionMgmtService.updateOption(Option.ID_C_HEADER_BANNER,adOption);
    operationMgmtService.addOperation(Operation.newOperation(request,Operation.OPERATION_CODE_C_UPDATE_AD_POS,Option.ID_C_HEADER_BANNER));
  }
  context.sendRedirect(Latkes.getServePath() + "/admin/ad");
}
