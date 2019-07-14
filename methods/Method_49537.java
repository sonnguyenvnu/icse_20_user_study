public static ScanMetrics cassandraRepair(String janusgraphPropertiesPath,String indexName,String relationType,String partitionerName) throws InterruptedException, IOException, ClassNotFoundException {
  Properties p=new Properties();
  FileInputStream fis=null;
  try {
    fis=new FileInputStream(janusgraphPropertiesPath);
    p.load(fis);
    return cassandraRepair(p,indexName,relationType,partitionerName);
  }
  finally {
    IOUtils.closeQuietly(fis);
  }
}
