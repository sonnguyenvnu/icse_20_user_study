public void complete(ModifiedTransformResponse<FileMetadataResponse> modifiedTransformResponse){
  FileMetadataResponse fileMetadataResponse=new FileMetadataResponse();
  String tableId=modifiedTransformResponse.getTable();
  Map<String,List<FileMetadataResponse.ParsedFileMetadata>> groupedMimeTypeMetadata=response.getResults().getRows().stream().map(r -> parseRow(r)).collect(Collectors.groupingBy(FileMetadataResponse.ParsedFileMetadata::getMimeType));
  Map<String,List<FileMetadataResponse.ParsedFileMetadata>> groupedMetadata=response.getResults().getRows().stream().map(r -> parseRow(r)).collect(Collectors.groupingBy(FileMetadataResponse.ParsedFileMetadata::getKey));
  Map<String,FileMetadataResponse.FileDataSet> fileDatasets=groupedMetadata.keySet().stream().collect(Collectors.toMap(key -> groupedMetadata.get(key).get(0).getFilePath(),key -> {
    FileMetadataResponse.FileDataSet dataSet=new FileMetadataResponse.FileDataSet();
    List<FileMetadataResponse.ParsedFileMetadata> files=groupedMetadata.get(key);
    dataSet.setFiles(files);
    FileMetadataResponse.ParsedFileMetadata firstFile=files.get(0);
    dataSet.setMimeType(firstFile.getMimeType());
    findSchemaParserForMimeType(firstFile.getMimeType()).ifPresent(schemaParserDescriptor -> {
      dataSet.setSchemaParser(schemaParserDescriptor);
      schemaParserDescriptor.getProperties().stream().filter(property -> property.getObjectProperty().equals("separatorChar")).findFirst().ifPresent(property -> {
        property.setValue(firstFile.getDelimiter());
      }
);
      schemaParserDescriptor.getProperties().stream().filter(property -> property.getObjectProperty().equals("rowTag")).findFirst().ifPresent(property -> {
        property.setValue(firstFile.getRowTag());
      }
);
      Optional<FileSchemaParser> fileSchemaParser=fileSchemaParser(schemaParserDescriptor);
      if (fileSchemaParser.isPresent() && fileSchemaParser.get() instanceof SparkFileSchemaParser) {
        List<String> paths=files.stream().map(parsedFileMetadata -> parsedFileMetadata.getFilePath()).collect(Collectors.toList());
        SampleFileSparkScript sparkScript=((SparkFileSchemaParser)fileSchemaParser.get()).getSparkScript(paths);
        dataSet.setSparkScript(sparkScript);
      }
    }
);
    return dataSet;
  }
));
  fileMetadataResponse.setDatasets(fileDatasets);
  modifiedTransformResponse.setResults(fileMetadataResponse);
  modifiedTransformResponse.setStatus(TransformResponse.Status.SUCCESS);
}
