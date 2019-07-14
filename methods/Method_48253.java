private boolean isCollection(String field){
  KeyInformation keyInformation=storeRetriever.get(field);
  return keyInformation != null && keyInformation.getCardinality() != Cardinality.SINGLE;
}
