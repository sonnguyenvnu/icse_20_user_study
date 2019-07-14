/** 
 * Shows kill browser page with the specified context.
 * @param context the specified context
 */
@RequestProcessing(value="/kill-browser",method=HttpMethod.GET) @Before(StopwatchStartAdvice.class) @After(StopwatchEndAdvice.class) public void showKillBrowser(final RequestContext context){
  final AbstractFreeMarkerRenderer renderer=new SkinRenderer(context,"other/kill-browser.ftl");
  final Map<String,Object> dataModel=renderer.getDataModel();
  final Map<String,String> langs=langPropsService.getAll(Locales.getLocale());
  dataModel.putAll(langs);
  Keys.fillRuntime(dataModel);
  dataModelService.fillMinified(dataModel);
}
