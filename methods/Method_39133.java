/** 
 * {@inheritDoc}
 */
@Override public Object intercept(final ActionRequest actionRequest) throws Exception {
  HttpServletRequest servletRequest=actionRequest.getHttpServletRequest();
  if (ServletUtil.isMultipartRequest(servletRequest)) {
    servletRequest=new MultipartRequestWrapper(servletRequest,fileUploader.get(),madvocEncoding.getEncoding());
    actionRequest.bind(servletRequest);
  }
  inject(actionRequest);
  final Object result=actionRequest.invoke();
  outject(actionRequest);
  return result;
}
