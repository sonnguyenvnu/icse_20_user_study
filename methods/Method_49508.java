public static void setHBaseAuthToken(Configuration configuration,Job job) throws IOException {
  String hbaseAuthentication=configuration.get("hbase.security.authentication");
  if (null != hbaseAuthentication && hbaseAuthentication.equals("kerberos")) {
    String quorumCfgKey="hbase.zookeeper.quorum";
    log.info("Obtaining HBase Auth Token from ZooKeeper quorum " + configuration.get(quorumCfgKey));
    final String className="org.apache.hadoop.hbase.security.User";
    try {
      Class<?> clazz=HBaseAuthHelper.class.getClassLoader().loadClass(className);
      Method getCurrent=clazz.getMethod("getCurrent");
      Object user=getCurrent.invoke(null);
      Method obtainAuthTokenForJob=clazz.getMethod("obtainAuthTokenForJob",Configuration.class,Job.class);
      obtainAuthTokenForJob.invoke(user,configuration,job);
      log.info("Obtained HBase Auth Token from ZooKeeper quorum {} for job {}",configuration.get(quorumCfgKey),job.getJobName());
    }
 catch (    Throwable t) {
      log.error("Failed to generate or store HBase auth token",t);
    }
  }
 else {
    log.info("Not obtaining HBase Auth Token for MapReduce job " + job.getJobName());
  }
}
