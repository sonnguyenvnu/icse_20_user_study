public IteratorSettingBuilder view(final View view){
  try {
    setting.addOption(AccumuloStoreConstants.VIEW,new String(view.toCompactJson(),CommonConstants.UTF_8));
  }
 catch (  final UnsupportedEncodingException e) {
    throw new SchemaException("Unable to deserialise view from JSON",e);
  }
  return this;
}
