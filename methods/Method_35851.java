public static TemplateCacheKey forProxyUrl(ResponseDefinition responseDefinition){
  return new TemplateCacheKey(responseDefinition,ResponseElement.PROXY_URL,"[proxyUrl]",null);
}
