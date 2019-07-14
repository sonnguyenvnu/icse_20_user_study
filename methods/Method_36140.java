@Override public void save(StubMapping stubMapping){
  StubMappingFileMetadata fileMetadata=fileNameMap.get(stubMapping.getId());
  if (fileMetadata == null) {
    fileMetadata=new StubMappingFileMetadata(SafeNames.makeSafeFileName(stubMapping),false);
  }
  if (fileMetadata.multi) {
    throw new NotWritableException("Stubs loaded from multi-mapping files are read-only, and therefore cannot be saved");
  }
  mappingsFileSource.writeTextFile(fileMetadata.path,writePrivate(stubMapping));
  fileNameMap.put(stubMapping.getId(),fileMetadata);
  stubMapping.setDirty(false);
}
