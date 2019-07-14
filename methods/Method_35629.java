public static MappingBuilder trace(UrlPattern urlPattern){
  return new BasicMappingBuilder(RequestMethod.TRACE,urlPattern);
}
