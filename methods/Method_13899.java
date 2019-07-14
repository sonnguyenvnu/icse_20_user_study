private static String makeRedirectUrl(ButterflyModule module,HttpServletRequest request) throws MalformedURLException {
  StringBuffer sb=new StringBuffer(module.getMountPoint().getMountPoint());
  sb.append("authorized?winname=");
  sb.append(ParsingUtilities.encode(request.getParameter("winname")));
  sb.append("&cb=");
  sb.append(ParsingUtilities.encode(request.getParameter("cb")));
  URL thisUrl=new URL(request.getRequestURL().toString());
  URL authorizedUrl=new URL(thisUrl,sb.toString());
  return authorizedUrl.toExternalForm();
}
