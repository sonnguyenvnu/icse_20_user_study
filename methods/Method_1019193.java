@CliCommand(value="connect",help="Connect to a hoodie dataset") public String connect(@CliOption(key={"path"},mandatory=true,help="Base Path of the dataset") final String path) throws IOException {
  boolean initialized=HoodieCLI.initConf();
  HoodieCLI.initFS(initialized);
  HoodieCLI.setTableMetadata(new HoodieTableMetaClient(HoodieCLI.conf,path));
  HoodieCLI.state=HoodieCLI.CLIState.DATASET;
  return "Metadata for table " + HoodieCLI.tableMetadata.getTableConfig().getTableName() + " loaded";
}
