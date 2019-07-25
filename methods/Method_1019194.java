@CliCommand(value="hdfsparquetimport",help="Imports Parquet dataset to a hoodie dataset") public String convert(@CliOption(key="upsert",mandatory=false,unspecifiedDefaultValue="false",help="Uses upsert API instead of the default insert API of WriteClient") boolean useUpsert,@CliOption(key="srcPath",mandatory=true,help="Base path for the input dataset") final String srcPath,@CliOption(key="targetPath",mandatory=true,help="Base path for the target hoodie dataset") final String targetPath,@CliOption(key="tableName",mandatory=true,help="Table name") final String tableName,@CliOption(key="tableType",mandatory=true,help="Table type") final String tableType,@CliOption(key="rowKeyField",mandatory=true,help="Row key field name") final String rowKeyField,@CliOption(key="partitionPathField",mandatory=true,help="Partition path field name") final String partitionPathField,@CliOption(key={"parallelism"},mandatory=true,help="Parallelism for hoodie insert") final String parallelism,@CliOption(key="schemaFilePath",mandatory=true,help="Path for Avro schema file") final String schemaFilePath,@CliOption(key="format",mandatory=true,help="Format for the input data") final String format,@CliOption(key="sparkMemory",mandatory=true,help="Spark executor memory") final String sparkMemory,@CliOption(key="retry",mandatory=true,help="Number of retries") final String retry) throws Exception {
  (new HDFSParquetImporter.FormatValidator()).validate("format",format);
  boolean initialized=HoodieCLI.initConf();
  HoodieCLI.initFS(initialized);
  String sparkPropertiesPath=Utils.getDefaultPropertiesFile(JavaConverters.mapAsScalaMapConverter(System.getenv()).asScala());
  SparkLauncher sparkLauncher=SparkUtil.initLauncher(sparkPropertiesPath);
  String cmd=SparkCommand.IMPORT.toString();
  if (useUpsert) {
    cmd=SparkCommand.UPSERT.toString();
  }
  sparkLauncher.addAppArgs(cmd,srcPath,targetPath,tableName,tableType,rowKeyField,partitionPathField,parallelism,schemaFilePath,sparkMemory,retry);
  Process process=sparkLauncher.launch();
  InputStreamConsumer.captureOutput(process);
  int exitCode=process.waitFor();
  if (exitCode != 0) {
    return "Failed to import dataset to hoodie format";
  }
  return "Dataset imported to hoodie format";
}
