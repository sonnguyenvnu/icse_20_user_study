@GetMapping("/data/standard/request") public String standardRequestArgs(HttpServletRequest request,Principal user,Locale locale){
  StringBuilder buffer=new StringBuilder();
  buffer.append("request = ").append(request).append(", ");
  buffer.append("userPrincipal = ").append(user).append(", ");
  buffer.append("requestLocale = ").append(locale);
  return buffer.toString();
}
