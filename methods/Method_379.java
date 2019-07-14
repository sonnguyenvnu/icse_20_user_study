private static void config(Properties properties){
{
    String featuresProperty=properties.getProperty("fastjson.serializerFeatures.MapSortField");
    int mask=SerializerFeature.MapSortField.getMask();
    if ("true".equals(featuresProperty)) {
      DEFAULT_GENERATE_FEATURE|=mask;
    }
 else     if ("false".equals(featuresProperty)) {
      DEFAULT_GENERATE_FEATURE&=~mask;
    }
  }
{
    if ("true".equals(properties.getProperty("parser.features.NonStringKeyAsString"))) {
      DEFAULT_PARSER_FEATURE|=Feature.NonStringKeyAsString.getMask();
    }
  }
{
    if ("true".equals(properties.getProperty("parser.features.ErrorOnEnumNotMatch")) || "true".equals(properties.getProperty("fastjson.parser.features.ErrorOnEnumNotMatch"))) {
      DEFAULT_PARSER_FEATURE|=Feature.ErrorOnEnumNotMatch.getMask();
    }
  }
{
    if ("false".equals(properties.getProperty("fastjson.asmEnable"))) {
      ParserConfig.getGlobalInstance().setAsmEnable(false);
      SerializeConfig.getGlobalInstance().setAsmEnable(false);
    }
  }
}
