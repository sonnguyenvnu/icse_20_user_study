private void run(@Nonnull final PrintStream out,@Nonnull final String... args){
  if (args.length < 4) {
    String msg="Proper Usage is: <targetDatabase> <entity> <partition> <path-to-policy-file>\n" + "You can optionally add: --hiveConf hive.setting=value --hiveConf hive.other.setting=value\n" + "You can optionally add: --storageLevel rdd_persistence_level_value\n" + "You can optionally add: --numPartitions number_of_rdd_partitions\n" + "You provided " + args.length + " args which are (comma separated): " + StringUtils.join(args,",");
    out.println(msg);
    throw new IllegalArgumentException(msg);
  }
  final SparkContext sparkContext=SparkContext.getOrCreate();
  try {
    final ValidatorConfiguration params=new ValidatorConfiguration(args);
    try (final ConfigurableApplicationContext ctx=new AnnotationConfigApplicationContext("com.thinkbiganalytics.spark")){
      final DataValidator app=ctx.getBean(DataValidator.class);
      final HiveContext hiveContext=new HiveContext(sparkContext);
      for (      final Param param : params.getHiveParams()) {
        log.info("Adding Hive parameter {}={}",param.getName(),param.getValue());
        hiveContext.setConf(param.getName(),param.getValue());
      }
      log.info("Deployment Mode - {}",hiveContext.sparkContext().getConf().get("spark.submit.deployMode"));
      Map<String,FieldPolicy> policyMap=ctx.getBean(FieldPolicyLoader.class).loadFieldPolicy(params.getFieldPolicyJsonPath());
      final DataValidatorResult results=app.validateTable(params.getTargetDatabase(),params.getFeedTableName(),params.getValidTableName(),params.getPartition(),params.getNumPartitions(),policyMap,hiveContext);
      log.info("Persistence level: {}",params.getStorageLevel());
      results.persist(StorageLevel.fromString(params.getStorageLevel()));
      app.saveInvalidToTable(params.getTargetDatabase(),params.getFeedTableName(),params.getInvalidTableName(),results,hiveContext);
      app.saveValidToTable(params.getTargetDatabase(),params.getFeedTableName(),params.getValidTableName(),results,hiveContext);
      app.saveProfileToTable(params.getTargetDatabase(),params.getProfileTableName(),params.getPartition(),results,hiveContext);
      results.unpersist();
    }
     log.info("Validator app finished");
  }
 catch (  Exception e) {
    log.error("Failed to perform validation: {}",e.toString(),e);
    throw e;
  }
}
