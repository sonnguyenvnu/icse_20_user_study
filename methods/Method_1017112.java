public static HttpContext create(final HttpServletRequest servletRequest){
  final Optional<String> xForwardedFor=Optional.ofNullable(servletRequest.getHeader("X-Forwarded-For"));
  final String remoteAddress=servletRequest.getRemoteAddr();
  final String clientAddress=xForwardedFor.orElse(remoteAddress);
  final Optional<String> userAgent=Optional.ofNullable(servletRequest.getHeader("User-Agent"));
  final Optional<String> clientId=Optional.ofNullable(servletRequest.getHeader("X-Client-Id"));
  return new HttpContext(remoteAddress,servletRequest.getRemoteHost(),clientAddress,userAgent,clientId);
}
