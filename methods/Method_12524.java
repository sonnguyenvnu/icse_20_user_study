private static Boolean isLegacyResponse(ClientResponse response){
  return response.headers().contentType().map(t -> ACTUATOR_V1_MEDIATYPE.isCompatibleWith(t) || APPLICATION_JSON.isCompatibleWith(t)).orElse(false);
}
