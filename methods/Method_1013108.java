public void run() throws Exception {
  StormSubmitter.submitTopology(config.benchName,getConf(),getBuilder().createTopology());
}
