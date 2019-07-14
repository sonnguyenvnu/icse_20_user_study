private void tryWithBroadcast(){
  this.bdThread=new Thread(){
    public void run(){
      for (int i=0; i < SubnetScanner.RETRY_COUNT; i++) {
        try {
          SmbFile smbFile=new SmbFile("smb://");
          smbFile.setConnectTimeout(5000);
          SmbFile[] listFiles=smbFile.listFiles();
          for (          SmbFile smbFile2 : listFiles) {
            SmbFile[] listFiles2=smbFile2.listFiles();
            for (            SmbFile files : listFiles2) {
              try {
                String substring=files.getName().substring(0,files.getName().length() - 1);
                UniAddress byName=UniAddress.getByName(substring);
                if (byName != null) {
                  SubnetScanner.this.onFound(new ComputerParcelable(substring,byName.getHostAddress()));
                }
              }
 catch (              Throwable e) {
              }
            }
          }
        }
 catch (        Throwable e2) {
        }
      }
    }
  }
;
  this.bdThread.start();
}
