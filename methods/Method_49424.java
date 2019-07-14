public Map<String,Object> getNewDocument(final List<IndexEntry> additions,KeyInformation.StoreRetriever information) throws BackendException {
  final Multimap<String,IndexEntry> unique=LinkedListMultimap.create();
  for (  final IndexEntry e : additions) {
    unique.put(e.field,e);
  }
  final Map<String,Object> doc=new HashMap<>();
  for (  final Map.Entry<String,Collection<IndexEntry>> add : unique.asMap().entrySet()) {
    final KeyInformation keyInformation=information.get(add.getKey());
    final Object value;
switch (keyInformation.getCardinality()) {
case SINGLE:
      value=convertToEsType(Iterators.getLast(add.getValue().iterator()).value,Mapping.getMapping(keyInformation));
    break;
case SET:
case LIST:
  value=add.getValue().stream().map(v -> convertToEsType(v.value,Mapping.getMapping(keyInformation))).filter(v -> {
    Preconditions.checkArgument(!(v instanceof byte[]),"Collections not supported for " + add.getKey());
    return true;
  }
).toArray();
break;
default :
value=null;
break;
}
doc.put(add.getKey(),value);
if (hasDualStringMapping(information.get(add.getKey())) && keyInformation.getDataType() == String.class) {
doc.put(getDualMappingName(add.getKey()),value);
}
}
return doc;
}
