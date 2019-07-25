public void run(String[] args){
  try {
    SparkMetadataExtractorArguments arguments=parseCommandLineArgs(args);
    setArguments(arguments);
    List<FileMetadata> list=parse(arguments.getPaths());
    int i=0;
  }
 catch (  Exception e) {
  }
}
