void call(final Iterator<Element> elements,final int partitionId,final long taskAttemptId) throws Exception {
  final SchemaUtils schemaUtils=new SchemaUtils(Schema.fromJson(schemaAsJson));
  final Map<String,ParquetWriter<Element>> groupToWriter=new HashMap<>();
  final Map<String,Path> groupToWriterPath=new HashMap<>();
  for (  final String group : schemaUtils.getGroups()) {
    groupToWriterPath.put(group,new Path(groupToDirectory.get(group) + "/input-" + partitionId + "-" + taskAttemptId + ".parquet"));
    groupToWriter.put(group,buildWriter(group,groupToWriterPath.get(group),schemaUtils));
  }
  writeData(elements,partitionId,taskAttemptId,groupToWriter);
  renameFiles(partitionId,taskAttemptId,schemaUtils.getGroups(),groupToWriterPath);
}
