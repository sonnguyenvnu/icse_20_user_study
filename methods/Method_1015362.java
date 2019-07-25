public static LegacyEndpointConverter health(){
  return new LegacyEndpointConverter(Endpoint.HEALTH,convertUsing(RESPONSE_TYPE_MAP,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertHealth));
}
