@Override public StormTopology _XXXXX_(Config config,StormEnvironment environment){
  MRHistoryJobConfig appConfig=MRHistoryJobConfig.newInstance(config);
  com.typesafe.config.Config jhfAppConf=appConfig.getConfig();
  final JobHistoryContentFilterBuilder builder=JobHistoryContentFilterBuilder.newBuilder().acceptJobFile().acceptJobConfFile();
  String[] confKeyPatternsSplit=jhfAppConf.getString("MRConfigureKeys.jobConfigKey").split(",");
  List<String> confKeyPatterns=new ArrayList<>(confKeyPatternsSplit.length);
  for (  String confKeyPattern : confKeyPatternsSplit) {
    confKeyPatterns.add(confKeyPattern.trim());
  }
  confKeyPatterns.add(Constants.JobConfiguration.CASCADING_JOB);
  confKeyPatterns.add(Constants.JobConfiguration.HIVE_JOB);
  confKeyPatterns.add(Constants.JobConfiguration.PIG_JOB);
  confKeyPatterns.add(Constants.JobConfiguration.SCOOBI_JOB);
  String jobNameKey=jhfAppConf.getString("MRConfigureKeys.jobNameKey");
  builder.setJobNameKey(jobNameKey);
  for (  String key : confKeyPatterns) {
    builder.includeJobKeyPatterns(Pattern.compile(key));
  }
  JobHistoryContentFilter filter=builder.build();
  TopologyBuilder topologyBuilder=new TopologyBuilder();
  String spoutName="mrHistoryJobSpout";
  int tasks=jhfAppConf.getInt("stormConfig.mrHistoryJobSpoutTasks");
  JobHistorySpout jobHistorySpout=new JobHistorySpout(filter,appConfig);
  topologyBuilder.setSpout(spoutName,jobHistorySpout,tasks).setNumTasks(tasks);
  StormStreamSink jobSinkBolt=environment.getStreamSink("MAP_REDUCE_JOB_STREAM",config);
  String jobSinkBoltName="JobKafkaSink";
  BoltDeclarer jobKafkaBoltDeclarer=topologyBuilder.setBolt(jobSinkBoltName,jobSinkBolt,jhfAppConf.getInt("stormConfig.jobKafkaSinkTasks")).setNumTasks(jhfAppConf.getInt("stormConfig.jobKafkaSinkTasks"));
  String spoutToJobSinkName=spoutName + "_to_" + jobSinkBoltName;
  jobKafkaBoltDeclarer.shuffleGrouping(spoutName,spoutToJobSinkName);
  StormStreamSink taskAttemptSinkBolt=environment.getStreamSink("MAP_REDUCE_TASK_ATTEMPT_STREAM",config);
  String taskAttemptSinkBoltName="TaskAttemptKafkaSink";
  BoltDeclarer taskAttemptKafkaBoltDeclarer=topologyBuilder.setBolt(taskAttemptSinkBoltName,taskAttemptSinkBolt,jhfAppConf.getInt("stormConfig.taskAttemptKafkaSinkTasks")).setNumTasks(jhfAppConf.getInt("stormConfig.taskAttemptKafkaSinkTasks"));
  String spoutToTaskAttemptSinkName=spoutName + "_to_" + taskAttemptSinkBoltName;
  taskAttemptKafkaBoltDeclarer.shuffleGrouping(spoutName,spoutToTaskAttemptSinkName);
  List<StreamPublisher> streamPublishers=new ArrayList<>();
  streamPublishers.add(new TaskAttemptStreamPublisher(spoutToTaskAttemptSinkName));
  streamPublishers.add(new JobRpcAnalysisStreamPublisher(spoutToJobSinkName));
  jobHistorySpout.setStreamPublishers(streamPublishers);
  return topologyBuilder.createTopology();
}