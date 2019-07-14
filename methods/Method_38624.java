@Override public void render(final ActionRequest actionRequest,final String resultValue) throws Exception {
  HttpServletRequest request=actionRequest.getHttpServletRequest();
  HttpServletResponse response=actionRequest.getHttpServletResponse();
  Object action=actionRequest.getAction();
  AppAction appAction=(AppAction)action;
  List<Violation> list=appAction.violations();
  String result=VtorUtil.createViolationsJsonString(request,list);
  if (jsonResponseContentType != null) {
    response.setContentType(jsonResponseContentType);
  }
  char[] chars=result.toCharArray();
  byte[] data=CharUtil.toByteArray(chars,madvocEncoding.getEncoding());
  OutputStream os=response.getOutputStream();
  os.write(data);
  os.flush();
}
