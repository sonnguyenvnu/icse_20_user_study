/** 
 * Non-blocking call to download and install a contribution in a new thread. Used when information about the progress of the download and install procedure is not of importance, such as if a contribution has to be installed at startup time.
 * @param url Direct link to the contribution.
 * @param ad The AvailableContribution to be downloaded and installed.
 */
static void downloadAndInstallOnStartup(final Base base,final URL url,final AvailableContribution ad){
  new Thread(new Runnable(){
    public void run(){
      String filename=url.getFile();
      filename=filename.substring(filename.lastIndexOf('/') + 1);
      try {
        File contribZip=File.createTempFile("download",filename);
        contribZip.setWritable(true);
        try {
          download(url,null,contribZip,null);
          final LocalContribution contribution=ad.install(base,contribZip,false,null);
          if (contribution != null) {
            try {
              EventQueue.invokeAndWait(new Runnable(){
                @Override public void run(){
                  listing.replaceContribution(ad,contribution);
                  base.refreshContribs(contribution.getType());
                  base.setUpdatesAvailable(listing.countUpdates(base));
                }
              }
);
            }
 catch (            InterruptedException e) {
              e.printStackTrace();
            }
catch (            InvocationTargetException e) {
              Throwable cause=e.getCause();
              if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
              }
 else {
                cause.printStackTrace();
              }
            }
          }
          contribZip.delete();
          handleUpdateFailedMarkers(ad,filename.substring(0,filename.lastIndexOf('.')));
        }
 catch (        Exception e) {
          String arg="contrib.startup.errors.download_install";
          System.err.println(Language.interpolate(arg,ad.getName()));
        }
      }
 catch (      IOException e) {
        String arg="contrib.startup.errors.temp_dir";
        System.err.println(Language.interpolate(arg,ad.getName()));
      }
    }
  }
,"Contribution Installer").start();
}
