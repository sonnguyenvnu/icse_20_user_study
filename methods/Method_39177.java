/** 
 * Redirects to the given location. Provided path is parsed, action is used as a value context.
 */
@Override public void render(final ActionRequest actionRequest,final Object resultValue) throws Exception {
  final Redirect redirectResult;
  if (resultValue == null) {
    redirectResult=Redirect.to(StringPool.EMPTY);
  }
 else {
    if (resultValue instanceof String) {
      redirectResult=Redirect.to((String)resultValue);
    }
 else {
      redirectResult=(Redirect)resultValue;
    }
  }
  final String resultBasePath=actionRequest.getActionRuntime().getResultBasePath();
  final String redirectPath=redirectResult.path();
  final String resultPath;
  if (redirectPath.startsWith("http://") || redirectPath.startsWith("https://")) {
    resultPath=redirectPath;
  }
 else {
    resultPath=resultMapper.resolveResultPathString(resultBasePath,redirectPath);
  }
  HttpServletRequest request=actionRequest.getHttpServletRequest();
  HttpServletResponse response=actionRequest.getHttpServletResponse();
  String path=resultPath;
  path=beanTemplateParser.parseWithBean(path,actionRequest.getAction());
  DispatcherUtil.redirect(request,response,path);
}
