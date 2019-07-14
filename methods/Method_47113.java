/** 
 * Sends a broadcast to start ftp server
 */
private void startServer(){
  getContext().sendBroadcast(new Intent(FtpService.ACTION_START_FTPSERVER).setPackage(getContext().getPackageName()));
}
