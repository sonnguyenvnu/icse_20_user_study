private void printMetadata(Metadata metadata,String prefix){
  for (int i=0; i < metadata.length(); i++) {
    logd(prefix + metadata.get(i));
  }
}
