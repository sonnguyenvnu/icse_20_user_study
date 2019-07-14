public static LegacyEndpointConverter env(){
  return new LegacyEndpointConverter(Endpoint.ENV,convertUsing(RESPONSE_TYPE_MAP,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertEnv));
}
