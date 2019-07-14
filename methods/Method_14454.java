static protected Project loadFromFile(File file,long id) throws Exception {
  ZipFile zipFile=new ZipFile(file);
  try {
    Pool pool=new Pool();
    ZipEntry poolEntry=zipFile.getEntry("pool.txt");
    if (poolEntry != null) {
      pool.load(zipFile.getInputStream(poolEntry));
    }
    return Project.loadFromInputStream(zipFile.getInputStream(zipFile.getEntry("data.txt")),id,pool);
  }
  finally {
    zipFile.close();
  }
}
