public static LegacyEndpointConverter liquibase(){
  return new LegacyEndpointConverter(Endpoint.LIQUIBASE,convertUsing(RESPONSE_TYPE_LIST_MAP,RESPONSE_TYPE_MAP,LegacyEndpointConverters::convertLiquibase));
}
