/** 
 * the call method activates the servlet as configured in the action definition
 * @return a service response
 * @throws APIException
 */
public ServiceResponse call() throws APIException {
  APIHandler a=this.getServlet();
  if (a == null)   throw new APIException(400,"this application does not handle a servlet " + this.getServletName());
  return a.serviceImpl(this.getQuery());
}
