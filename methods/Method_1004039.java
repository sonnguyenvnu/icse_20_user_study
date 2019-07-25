private void authenticate(HttpServletRequest request,HttpServletResponse response){
  Optional<String> urlToAuthenticate=Optional.ofNullable(request.getHeader("X-Original-URI"));
  Optional<String> path=urlToAuthenticate.flatMap(s -> {
    try {
      return Optional.of(new URI(s).getPath());
    }
 catch (    URISyntaxException e) {
      LOGGER.error("Failed to parse url [" + s + "]",e);
      return Optional.empty();
    }
  }
);
  Optional<Matcher> matcher=path.flatMap(p -> getRegexStream().map(regex -> regex.matcher(p)).filter(Matcher::find).findFirst());
  Optional<String> host=matcher.map(res -> res.group("host"));
  Optional<String> port=matcher.map(res -> res.group("port"));
  Integer httpCode=StreamSupport.stream(getRegistry().getAllProxies().spliterator(),false).filter(proxy -> proxy instanceof DockerSeleniumRemoteProxy).map(proxy -> ((DockerSeleniumRemoteProxy)proxy).getRegistration()).filter(reg -> host.equals(Optional.of(reg.getIpAddress())) && port.equals(Optional.of(reg.getNoVncPort().toString()))).findAny().map(x -> AUTHORISED).orElse(UNAUTHORISED);
  response.setStatus(httpCode);
}
