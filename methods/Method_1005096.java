public GraphPartitioner call() throws IOException {
  final SchemaUtils schemaUtils=new SchemaUtils(schema);
  final GraphPartitioner graphPartitioner=new GraphPartitioner();
  for (  final String group : schema.getGroups()) {
    LOGGER.info("Calculating GroupPartitioner for group {}",group);
    final GafferGroupObjectConverter converter=schemaUtils.getConverter(group);
    final List<PartitionKey> partitionKeys=new ArrayList<>();
    final Path groupPath=new Path(path,ParquetStore.getGroupSubDir(group,false));
    final FileStatus[] files=fs.listStatus(groupPath,p -> p.getName().endsWith(".parquet"));
    final SortedSet<Path> sortedFiles=new TreeSet<>();
    Arrays.stream(files).map(f -> f.getPath()).forEach(sortedFiles::add);
    final Path[] sortedPaths=sortedFiles.toArray(new Path[]{});
    LOGGER.debug("Found {} files in {}",files.length,groupPath);
    for (int i=1; i < sortedPaths.length; i++) {
      LOGGER.debug("Reading first line of {}",sortedPaths[i]);
      final ParquetReader<Element> reader=new ParquetElementReader.Builder<Element>(sortedPaths[i]).isEntity(schema.getEntityGroups().contains(group)).usingConverter(converter).build();
      final Element element=reader.read();
      if (null == element) {
        throw new IOException("No first element in file " + files[i].getPath() + " - empty files are supposed to be removed");
      }
      reader.close();
      final Object[] parquetObjects=converter.corePropertiesToParquetObjects(element);
      final PartitionKey key=new PartitionKey(parquetObjects);
      partitionKeys.add(key);
    }
    final GroupPartitioner groupPartitioner=new GroupPartitioner(group,partitionKeys);
    graphPartitioner.addGroupPartitioner(group,groupPartitioner);
    LOGGER.info("GroupPartitioner for group {} is {}",group,groupPartitioner);
  }
  for (  final String group : schema.getEdgeGroups()) {
    LOGGER.info("Calculating GroupPartitioner for reversed edge group {}",group);
    final GafferGroupObjectConverter converter=schemaUtils.getConverter(group);
    final List<PartitionKey> partitionKeys=new ArrayList<>();
    final Path groupPath=new Path(path,ParquetStore.getGroupSubDir(group,true));
    final FileStatus[] files=fs.listStatus(groupPath,p -> p.getName().endsWith(".parquet"));
    final SortedSet<Path> sortedFiles=new TreeSet<>();
    Arrays.stream(files).map(f -> f.getPath()).forEach(sortedFiles::add);
    final Path[] sortedPaths=sortedFiles.toArray(new Path[]{});
    LOGGER.debug("Found {} files in {}",files.length,groupPath);
    for (int i=1; i < sortedPaths.length; i++) {
      LOGGER.debug("Reading first line of {}",sortedPaths[i]);
      final ParquetReader<Element> reader=new ParquetElementReader.Builder<Element>(sortedPaths[i]).isEntity(false).usingConverter(converter).build();
      final Edge edge=(Edge)reader.read();
      if (null == edge) {
        throw new IOException("No first edge in file " + files[i].getPath() + " - empty files are supposed to be removed");
      }
      reader.close();
      final Object[] parquetObjects=converter.corePropertiesToParquetObjectsForReversedEdge(edge);
      final PartitionKey key=new PartitionKey(parquetObjects);
      partitionKeys.add(key);
    }
    final GroupPartitioner groupPartitioner=new GroupPartitioner(group,partitionKeys);
    graphPartitioner.addGroupPartitionerForReversedEdges(group,groupPartitioner);
  }
  return graphPartitioner;
}
