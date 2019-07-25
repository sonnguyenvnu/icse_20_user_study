protected void capture(ServletRequest request,ServletResponse response){
  HttpServletRequest httpReq=(HttpServletRequest)request;
  HttpServletResponse httpResp=(HttpServletResponse)response;
  contextHolder.set(new HttpCallbackContext(httpReq,httpResp));
}
