protected static void saveToFile(ProjectMetadata projectMeta,File metadataFile) throws IOException {
  Writer writer=new OutputStreamWriter(new FileOutputStream(metadataFile));
  try {
    ParsingUtilities.defaultWriter.writeValue(writer,projectMeta);
  }
  finally {
    writer.close();
  }
}
