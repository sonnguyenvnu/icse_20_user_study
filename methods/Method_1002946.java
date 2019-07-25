@Override public HttpResponse serve(ServiceRequestContext ctx,AggregatedHttpRequest req,String defaultHostname,SamlPortConfig portConfig){
  final HttpData metadata=metadataMap.computeIfAbsent(defaultHostname,h -> {
    try {
      final Element element=SamlMessageUtil.serialize(buildMetadataEntityDescriptorElement(h,portConfig));
      final HttpData newMetadata=HttpData.ofUtf8(nodeToString(element));
      logger.debug("SAML service provider metadata has been prepared for: {}.",h);
      return newMetadata;
    }
 catch (    Throwable cause) {
      logger.warn("{} Unexpected metadata request.",ctx,cause);
      return HttpData.EMPTY_DATA;
    }
  }
);
  if (metadata != HttpData.EMPTY_DATA) {
    return HttpResponse.of(HTTP_HEADERS,metadata);
  }
 else {
    return HttpResponse.of(HttpStatus.NOT_FOUND);
  }
}
