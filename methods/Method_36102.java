@Override public String getClientIp(){
  String forwardedForHeader=this.getHeader("X-Forwarded-For");
  if (forwardedForHeader != null && forwardedForHeader.length() > 0) {
    return forwardedForHeader;
  }
  return request.getRemoteAddr();
}
