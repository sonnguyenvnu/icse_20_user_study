private JobTypeConfiguration getJobTypeConfiguration(final JobCoreConfiguration coreConfig,final JobType jobType,final String jobClass,final boolean streamingProcess,final String scriptCommandLine){
  Preconditions.checkNotNull(jobType,"jobType cannot be null.");
switch (jobType) {
case SIMPLE:
    Preconditions.checkArgument(!Strings.isNullOrEmpty(jobClass),"jobClass cannot be empty.");
  return new SimpleJobConfiguration(coreConfig,jobClass);
case DATAFLOW:
Preconditions.checkArgument(!Strings.isNullOrEmpty(jobClass),"jobClass cannot be empty.");
return new DataflowJobConfiguration(coreConfig,jobClass,streamingProcess);
case SCRIPT:
return new ScriptJobConfiguration(coreConfig,scriptCommandLine);
default :
throw new UnsupportedOperationException(String.valueOf(jobType));
}
}
