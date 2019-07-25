/** 
 * Initializes the action object based on the HTTP request.
 */
protected void init(HttpServletRequest req,HttpServletResponse resp){
  this.req=req;
  this.resp=resp;
  initAuthInfo();
}
