private boolean anyFilesAreMultiMapping(){
  return any(fileNameMap.values(),new Predicate<StubMappingFileMetadata>(){
    @Override public boolean apply(    StubMappingFileMetadata input){
      return input.multi;
    }
  }
);
}
