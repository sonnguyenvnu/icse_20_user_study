static protected ProjectMetadata loadFromFile(File metadataFile) throws Exception {
  FileReader reader=new FileReader(metadataFile);
  return ParsingUtilities.mapper.readValue(reader,ProjectMetadata.class);
}
