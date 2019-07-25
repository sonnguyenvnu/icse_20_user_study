private GetFieldMappingsResponse merge(AtomicReferenceArray<Object> indexResponses){
  Map<String,Map<String,Map<String,GetFieldMappingsResponse.FieldMappingMetaData>>> mergedResponses=new HashMap<>();
  for (int i=0; i < indexResponses.length(); i++) {
    Object element=indexResponses.get(i);
    if (element instanceof GetFieldMappingsResponse) {
      GetFieldMappingsResponse response=(GetFieldMappingsResponse)element;
      mergedResponses.putAll(response.mappings());
    }
  }
  return new GetFieldMappingsResponse(unmodifiableMap(mergedResponses));
}
