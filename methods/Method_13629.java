private void initFallback(){
  FallbackProperties fallbackProperties=gatewayProperties.getFallback();
  if (fallbackProperties == null || StringUtil.isBlank(fallbackProperties.getMode())) {
    return;
  }
  if (ConfigConstants.FALLBACK_MSG_RESPONSE.equals(fallbackProperties.getMode())) {
    if (StringUtil.isNotBlank(fallbackProperties.getResponseBody())) {
      GatewayCallbackManager.setBlockHandler(new BlockRequestHandler(){
        @Override public Mono<ServerResponse> handleRequest(        ServerWebExchange exchange,        Throwable t){
          return ServerResponse.status(fallbackProperties.getResponseStatus()).contentType(MediaType.valueOf(fallbackProperties.getContentType())).body(fromObject(fallbackProperties.getResponseBody()));
        }
      }
);
      logger.info("[Sentinel SpringCloudGateway] using AnonymousBlockRequestHandler, responseStatus: " + fallbackProperties.getResponseStatus() + ", responseBody: " + fallbackProperties.getResponseStatus());
    }
  }
  String redirectUrl=fallbackProperties.getRedirect();
  if (ConfigConstants.FALLBACK_REDIRECT.equals(fallbackProperties.getMode()) && StringUtil.isNotBlank(redirectUrl)) {
    GatewayCallbackManager.setBlockHandler(new RedirectBlockRequestHandler(redirectUrl));
    logger.info("[Sentinel SpringCloudGateway] using RedirectBlockRequestHandler, redirectUrl: " + redirectUrl);
  }
}
