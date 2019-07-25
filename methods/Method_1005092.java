/** 
 * For each group, sorts the data. If the group requires aggregation then the aggregated data from the previous call to  {@link AddElementsFromRDD#aggregateNewAndOldData} is sorted. If the group does not require aggregationthen the existing data and the new data are sorted in one operation.
 * @throws OperationException if an {@link IOException} is thrown
 */
private void sort() throws OperationException {
  try {
    for (    final String group : schemaUtils.getGroups()) {
      final List<String> inputFiles=new ArrayList<>();
      final String outputDir=getDirectory(group,true,true,false);
      if (schemaUtils.getGafferSchema().getAggregatedGroups().contains(group)) {
        final String inputDir=getDirectory(group,false,true,false);
        final FileStatus[] inputFilesFS=fs.listStatus(new Path(inputDir),path -> path.getName().endsWith(".parquet"));
        Arrays.stream(inputFilesFS).map(f -> f.getPath().toString()).forEach(inputFiles::add);
      }
 else {
        final String groupDirectoryNewData=getDirectory(group,false,false,false);
        final FileStatus[] newData=fs.listStatus(new Path(groupDirectoryNewData),path -> path.getName().endsWith(".parquet"));
        Arrays.stream(newData).map(f -> f.getPath().toString()).forEach(inputFiles::add);
        final List<Path> existingData=store.getFilesForGroup(group);
        existingData.stream().map(p -> p.toString()).forEach(inputFiles::add);
      }
      sort(group,false,inputFiles,outputDir);
    }
  }
 catch (  final IOException e) {
    throw new OperationException("IOException sorting data",e);
  }
}
