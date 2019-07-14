@Override protected void setResponseContentType(HttpServletRequest request,HttpServletResponse response){
  if (getJsonpParameterValue(request) != null) {
    response.setContentType(DEFAULT_JSONP_CONTENT_TYPE);
  }
 else {
    super.setResponseContentType(request,response);
  }
}
