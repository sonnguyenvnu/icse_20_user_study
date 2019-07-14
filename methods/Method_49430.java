private Map<String,Object> getAdditionDoc(KeyInformation.IndexRetriever information,String store,IndexMutation mutation) throws PermanentBackendException {
  final Map<String,Object> doc=new HashMap<>();
  for (  final IndexEntry e : mutation.getAdditions()) {
    final KeyInformation keyInformation=information.get(store).get(e.field);
    if (keyInformation.getCardinality() == Cardinality.SINGLE) {
      doc.put(e.field,convertToEsType(e.value,Mapping.getMapping(keyInformation)));
      if (hasDualStringMapping(keyInformation)) {
        doc.put(getDualMappingName(e.field),convertToEsType(e.value,Mapping.getMapping(keyInformation)));
      }
    }
  }
  return doc;
}
