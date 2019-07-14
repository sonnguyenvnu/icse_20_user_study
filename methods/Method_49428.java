private String getAdditionScript(KeyInformation.IndexRetriever information,String storeName,IndexMutation mutation) throws PermanentBackendException {
  final StringBuilder script=new StringBuilder();
  for (  final IndexEntry e : mutation.getAdditions()) {
    final KeyInformation keyInformation=information.get(storeName).get(e.field);
    final Cardinality cardinality=keyInformation.getCardinality();
    if (cardinality != Cardinality.SET && cardinality != Cardinality.LIST) {
      continue;
    }
    String value=convertToJsType(e.value,compat.scriptLang(),Mapping.getMapping(keyInformation));
    appendAdditionScript(script,value,e.field,cardinality == Cardinality.SET);
    if (hasDualStringMapping(keyInformation)) {
      appendAdditionScript(script,value,getDualMappingName(e.field),cardinality == Cardinality.SET);
    }
  }
  return script.toString();
}
