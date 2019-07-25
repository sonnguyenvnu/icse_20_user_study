/** 
 * ????????@ModelAttribute ?SpringMVC?????????@ModelAttribute?????????????? ??????????????????????
 * @param request
 * @param response
 * @param session
 */
@ModelAttribute public void before(HttpServletRequest request,HttpServletResponse response,HttpSession session){
  this.request=request;
  this.response=response;
  this.session=session;
  if ((request.getRequestURI().indexOf("login") < 0 || request.getRequestURI().indexOf("authcode") < 0) && null == session.getAttribute("USER_ACCOUNT")) {
    try {
      this.login();
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
}
