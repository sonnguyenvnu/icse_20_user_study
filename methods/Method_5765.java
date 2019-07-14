private static Map<String,byte[]> applyMutations(Map<String,byte[]> otherMetadata,ContentMetadataMutations mutations){
  HashMap<String,byte[]> metadata=new HashMap<>(otherMetadata);
  removeValues(metadata,mutations.getRemovedValues());
  addValues(metadata,mutations.getEditedValues());
  return metadata;
}
