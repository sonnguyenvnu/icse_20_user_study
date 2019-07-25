@Override public void start(final CoprocessorEnvironment e) throws IOException {
  final String schemaJson=StringUtil.unescapeComma(e.getConfiguration().get(HBaseStoreConstants.SCHEMA));
  schema=Schema.fromJson(Bytes.toBytes(schemaJson));
  serialisation=new ElementSerialisation(schema);
}
