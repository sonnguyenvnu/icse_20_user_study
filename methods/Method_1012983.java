private static ZallyApi connect(){
  final String zallyUrl=ServiceManager.getService(ZallySettings.class).getZallyUrl();
  if (zallyUrl == null || zallyUrl.isEmpty()) {
    throw new RuntimeException("Zally URL is missing");
  }
  final Gson gson=new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
  final Decoder decoder=new GsonDecoder(gson);
  return Feign.builder().encoder(new GsonEncoder()).decoder(decoder).errorDecoder(new LintingResponseErrorDecoder()).logger(new Logger.ErrorLogger()).logLevel(Logger.Level.BASIC).target(ZallyApi.class,zallyUrl);
}
