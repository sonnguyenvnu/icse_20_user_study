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
    LOGGER.info("Not sorting data for group {} as list of input files that exist is empty");
    return null;
  }
  LOGGER.info("Sorting data in files {} by columns {} to one file in output directory {}",StringUtils.join(inputFilesThatExist,','),StringUtils.join(sortColumns,','),outputDir);
  spark.read().parquet(inputFilesThatExist.toArray(new String[]{})).sort(firstSortColumn,otherSortColumns.stream().toArray(String[]::new)).coalesce(1).write().option("compression",compressionCodecName.name()).parquet(outputDir);
  return null;
}
