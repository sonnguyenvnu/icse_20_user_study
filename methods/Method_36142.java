@Override public void loadMappingsInto(StubMappings stubMappings){
  if (!mappingsFileSource.exists()) {
    return;
  }
  Iterable<TextFile> mappingFiles=filter(mappingsFileSource.listFilesRecursively(),byFileExtension("json"));
  for (  TextFile mappingFile : mappingFiles) {
    try {
      StubMappingCollection stubCollection=Json.read(mappingFile.readContentsAsString(),StubMappingCollection.class);
      for (      StubMapping mapping : stubCollection.getMappingOrMappings()) {
        mapping.setDirty(false);
        stubMappings.addMapping(mapping);
        StubMappingFileMetadata fileMetadata=new StubMappingFileMetadata(mappingFile.getPath(),stubCollection.isMulti());
        fileNameMap.put(mapping.getId(),fileMetadata);
      }
    }
 catch (    JsonException e) {
      throw new MappingFileException(mappingFile.getPath(),e.getErrors().first().getDetail());
    }
  }
}
