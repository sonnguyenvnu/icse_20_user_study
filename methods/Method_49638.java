private Map<String,Object> collectFieldValues(List<IndexEntry> content,String collectionName,KeyInformation.IndexRetriever information) throws BackendException {
  final Map<String,Object> docs=new HashMap<>();
  for (  final IndexEntry addition : content) {
    final KeyInformation keyInformation=information.get(collectionName,addition.field);
switch (keyInformation.getCardinality()) {
case SINGLE:
      docs.put(addition.field,convertValue(addition.value));
    break;
case SET:
  if (!docs.containsKey(addition.field)) {
    docs.put(addition.field,new HashSet<>());
  }
((Set<Object>)docs.get(addition.field)).add(convertValue(addition.value));
break;
case LIST:
if (!docs.containsKey(addition.field)) {
docs.put(addition.field,new ArrayList<>());
}
((List<Object>)docs.get(addition.field)).add(convertValue(addition.value));
break;
}
}
return docs;
}
