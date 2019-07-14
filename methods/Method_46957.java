@Override public void onClick(){
  unlockAndRun(() -> {
    if (FtpService.isRunning()) {
      getApplicationContext().sendBroadcast(new Intent(FtpService.ACTION_STOP_FTPSERVER).setPackage(getPackageName()));
    }
 else {
      if (FtpService.isConnectedToWifi(getApplicationContext()) || FtpService.isConnectedToLocalNetwork(getApplicationContext()) || FtpService.isEnabledWifiHotspot(getApplicationContext())) {
        Intent i=new Intent(FtpService.ACTION_START_FTPSERVER).setPackage(getPackageName());
        i.putExtra(FtpService.TAG_STARTED_BY_TILE,true);
        getApplicationContext().sendBroadcast(i);
      }
 else {
        Toast.makeText(getApplicationContext(),getString(R.string.ftp_no_wifi),Toast.LENGTH_LONG).show();
      }
    }
  }
);
}
