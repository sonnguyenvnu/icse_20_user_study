@Override public OperationException call() throws IOException {
  final String firstSortColumn=sortColumns.get(0);
  final List<String> otherSortColumns=sortColumns.subList(1,sortColumns.size());
  final List<String> inputFilesThatExist=new ArrayList<>();
  for (  final String file : inputFiles) {
    if (!fs.exists(new Path(file))) {
      LOGGER.info("Ignoring file {} as it does not exist",file);
    }
 else {
      inputFilesThatExist.add(file);
    }
  }
  if (inputFilesThatExist.isEmpty()) {
    LOGGER.info("Not sorting data for group {} as list of input files that exist is empty",group);
    return null;
  }
  LOGGER.info("Sorting data in {} files by columns {} to {} files in output directory {}",inputFilesThatExist.size(),StringUtils.join(sortColumns,','),numberOfOutputFiles,outputDir);
  final ExtractKeyFromRow extractKeyFromRow=new ExtractKeyFromRow(new HashSet<>(),schemaUtils.getColumnToPaths(group),schemaUtils.getEntityGroups().contains(group),isReversed);
  LOGGER.info("Sampling data from {} input files to identify split points for sorting",inputFilesThatExist.size());
  final List<Seq<Object>> rows=spark.read().parquet(inputFilesThatExist.toArray(new String[]{})).javaRDD().map(extractKeyFromRow).takeSample(false,10000,1234567890L);
  LOGGER.info("Obtained {} rows in the sample",rows.size());
  final TreeSet<Seq<Object>> sortedRows=new TreeSet<>(new SeqComparator());
  sortedRows.addAll(rows);
  final TreeSet<Seq<Object>> splitPoints=new TreeSet<>(new SeqComparator());
  int desiredNumberOfSplits=numberOfOutputFiles - 1;
  long outputEveryNthRecord;
  if (sortedRows.size() < 2 || desiredNumberOfSplits < 1) {
    outputEveryNthRecord=1;
  }
 else {
    outputEveryNthRecord=sortedRows.size() / desiredNumberOfSplits;
  }
  if (outputEveryNthRecord < 1) {
    outputEveryNthRecord=1;
  }
  int numberOfSplitsOutput=0;
  int count=0;
  for (  final Seq<Object> seq : sortedRows) {
    count++;
    if (0 == count % outputEveryNthRecord) {
      splitPoints.add(seq);
      numberOfSplitsOutput++;
    }
    if (numberOfSplitsOutput >= desiredNumberOfSplits) {
      break;
    }
  }
  LOGGER.info("Found {} split points",splitPoints.size());
  final SeqObjectPartitioner partitioner=new SeqObjectPartitioner(numberOfOutputFiles,splitPoints);
  LOGGER.info("Partitioning data using split points and sorting within partition, outputting to {}",outputDir);
  final JavaRDD<Row> partitionedData=spark.read().parquet(inputFilesThatExist.toArray(new String[]{})).javaRDD().keyBy(new ExtractKeyFromRow(new HashSet<>(),schemaUtils.getColumnToPaths(group),schemaUtils.getEntityGroups().contains(group),isReversed)).partitionBy(partitioner).values();
  LOGGER.info("Sorting data within partitions, outputting to {}",outputDir);
  spark.createDataFrame(partitionedData,schemaUtils.getSparkSchema(group)).sortWithinPartitions(firstSortColumn,otherSortColumns.stream().toArray(String[]::new)).write().option("compression",compressionCodecName.name()).parquet(outputDir);
  final FileStatus[] sortedFiles=fs.listStatus(new Path(outputDir),path -> path.getName().endsWith(".parquet"));
  final SortedSet<Path> sortedSortedFiles=new TreeSet<>();
  Arrays.stream(sortedFiles).map(FileStatus::getPath).forEach(sortedSortedFiles::add);
  final Path[] sortedSortedPaths=sortedSortedFiles.toArray(new Path[]{});
  LOGGER.info("Renaming part-* files to partition-* files, removing empty files (part-* files are in directory {})",outputDir);
  int counter=0;
  for (int i=0; i < sortedSortedPaths.length; i++) {
    final Path path=sortedSortedPaths[i];
    final boolean isEmpty=isFileEmpty(path);
    if (isEmpty) {
      LOGGER.debug("Deleting empty file {}",path);
      fs.delete(path,false);
    }
 else {
      final Path newPath=new Path(outputDir + ParquetStore.getFile(counter));
      LOGGER.debug("Renaming {} to {}",path,newPath);
      fs.rename(path,newPath);
      counter++;
    }
  }
  return null;
}
