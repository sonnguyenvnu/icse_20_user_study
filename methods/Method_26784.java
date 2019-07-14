public static boolean isAjaxRequest(WebRequest webRequest){
  String requestedWith=webRequest.getHeader("X-Requested-With");
  return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
}
