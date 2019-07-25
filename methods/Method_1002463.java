public static void run(String resolverPath,String optionalDefault,boolean overrideNamespace,String targetDirectoryPath,String[] sources) throws IOException {
  final AvroSchemaGenerator generator=new AvroSchemaGenerator(new Config(resolverPath));
  if (optionalDefault != null) {
    final OptionalDefaultMode optionalDefaultMode=OptionalDefaultMode.valueOf(optionalDefault.toUpperCase());
    generator.getDataToAvroSchemaTranslationOptions().setOptionalDefaultMode(optionalDefaultMode);
  }
  generator.getDataToAvroSchemaTranslationOptions().setOverrideNamespace(overrideNamespace);
  if (overrideNamespace) {
    targetDirectoryPath+="/" + AVRO_PREFIX;
  }
  generator.generate(targetDirectoryPath,sources);
}
