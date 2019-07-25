@Override public List<FileMetadata> parse(String[] filePaths){
  List<DataFrame> dataFrameList=new ArrayList<>();
  for (  String path : filePaths) {
    DataFrame df=sqlContext.read().format("com.thinkbiganalytics.spark.file.metadata").load(path);
    dataFrameList.add(df);
  }
  DataFrame unionDf=SparkUtil.unionAll(dataFrameList);
  Encoder<FileMetadata> encoder=Encoders.bean(FileMetadata.class);
  Dataset dataset=unionDf.as(encoder);
  return dataset.collectAsList();
}
