public void putDrawerMetadata(MenuItem item,MenuMetadata metadata){
  menuMetadataMap.put(item,metadata);
  if (metadata.path != null)   tree.put(metadata.path,item.getItemId());
}
