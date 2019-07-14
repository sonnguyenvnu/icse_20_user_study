public static ScanMetrics hbaseRepair(String janusgraphPropertiesPath,String indexName,String relationType) throws InterruptedException, IOException, ClassNotFoundException {
  Properties p=new Properties();
  FileInputStream fis=null;
  try {
    fis=new FileInputStream(janusgraphPropertiesPath);
    p.load(fis);
    return hbaseRepair(p,indexName,relationType);
  }
  finally {
    IOUtils.closeQuietly(fis);
  }
}
