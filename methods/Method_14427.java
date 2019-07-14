protected void loadChange(HistoryEntry historyEntry,File file) throws Exception {
  ZipFile zipFile=new ZipFile(file);
  try {
    Pool pool=new Pool();
    ZipEntry poolEntry=zipFile.getEntry("pool.txt");
    if (poolEntry != null) {
      pool.load(new InputStreamReader(zipFile.getInputStream(poolEntry)));
    }
    historyEntry.setChange(History.readOneChange(zipFile.getInputStream(zipFile.getEntry("change.txt")),pool));
  }
  finally {
    zipFile.close();
  }
}
