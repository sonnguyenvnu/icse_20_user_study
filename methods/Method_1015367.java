public static LegacyEndpointConverter flyway(){
  return new LegacyEndpointConverter(Endpoint.FLYWAY,convertUsing(RESPONSE_TYPE_LIST_MAP,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertFlyway));
}
