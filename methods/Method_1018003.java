@Override public List<FileMetadata> parse(String[] filePaths){
  List<Dataset> dataFrameList=new ArrayList<>();
  for (  String path : filePaths) {
    Dataset df=(Dataset)sqlContext.read().format("com.thinkbiganalytics.spark.file.metadata").load(path);
    dataFrameList.add(df);
  }
  Dataset unionDf=unionAll(dataFrameList);
  Encoder<FileMetadata> encoder=Encoders.bean(FileMetadata.class);
  Dataset fileData=unionDf.as(encoder);
  return fileData.collectAsList();
}
