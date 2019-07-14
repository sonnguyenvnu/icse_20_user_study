public void checkReports(){
  new Thread(() -> {
    if (config.deleteOldUnsentReportsOnApplicationStart()) {
      deleteUnsentReportsFromOldAppVersion();
    }
  }
).start();
}
