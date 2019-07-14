public static ScanMetrics runJob(org.apache.hadoop.conf.Configuration hadoopConf,Class<? extends InputFormat> inputFormat,String jobName,Class<? extends Mapper> mapperClass) throws IOException, InterruptedException, ClassNotFoundException {
  Job job=Job.getInstance(hadoopConf);
  job.setJarByClass(mapperClass);
  job.setJobName(jobName);
  job.setOutputKeyClass(NullWritable.class);
  job.setOutputValueClass(NullWritable.class);
  job.setMapOutputKeyClass(NullWritable.class);
  job.setMapOutputValueClass(NullWritable.class);
  job.setNumReduceTasks(0);
  job.setMapperClass(mapperClass);
  job.setOutputFormatClass(NullOutputFormat.class);
  job.setInputFormatClass(inputFormat);
  boolean success=job.waitForCompletion(true);
  if (!success) {
    String f;
    try {
      f=String.format("MapReduce JobID %s terminated abnormally: %s",job.getJobID().toString(),HadoopCompatLoader.DEFAULT_COMPAT.getJobFailureString(job));
    }
 catch (    RuntimeException e) {
      f="Job failed (unable to read job status programmatically -- see MapReduce logs for information)";
    }
    throw new IOException(f);
  }
 else {
    return DEFAULT_COMPAT.getMetrics(job.getCounters());
  }
}
