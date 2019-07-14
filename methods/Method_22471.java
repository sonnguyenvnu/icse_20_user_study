/** 
 * Blocking call to download and install a set of libraries. Used when a list of libraries have to be installed while forcing the user to not modify anything and providing feedback via the console status area, such as when the user tries to run a sketch that imports uninstaled libraries.
 * @param list The list of AvailableContributions to be downloaded and installed.
 */
static public void downloadAndInstallOnImport(final Base base,final List<AvailableContribution> list){
  Editor editor=base.getActiveEditor();
  editor.getTextArea().setEditable(false);
  List<String> installedLibList=new ArrayList<>();
  boolean isPrevDone=false;
  for (  final AvailableContribution contrib : list) {
    if (contrib.getType() != ContributionType.LIBRARY) {
      continue;
    }
    try {
      URL url=new URL(contrib.link);
      String filename=url.getFile();
      filename=filename.substring(filename.lastIndexOf('/') + 1);
      try {
        File contribZip=File.createTempFile("download",filename);
        contribZip.setWritable(true);
        try {
          String statusMsg=editor.getStatusMessage();
          if (isPrevDone) {
            String status=statusMsg + " " + Language.interpolate("contrib.import.progress.download",contrib.name);
            editor.statusNotice(status);
          }
 else {
            String arg="contrib.import.progress.download";
            String status=Language.interpolate(arg,contrib.name);
            editor.statusNotice(status);
          }
          isPrevDone=false;
          download(url,null,contribZip,null);
          String arg="contrib.import.progress.install";
          editor.statusNotice(Language.interpolate(arg,contrib.name));
          final LocalContribution contribution=contrib.install(base,contribZip,false,null);
          if (contribution != null) {
            try {
              EventQueue.invokeAndWait(new Runnable(){
                @Override public void run(){
                  listing.replaceContribution(contrib,contribution);
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
          installedLibList.add(contrib.name);
          isPrevDone=true;
          arg="contrib.import.progress.done";
          editor.statusNotice(Language.interpolate(arg,contrib.name));
        }
 catch (        Exception e) {
          String arg="contrib.startup.errors.download_install";
          System.err.println(Language.interpolate(arg,contrib.getName()));
        }
      }
 catch (      IOException e) {
        String arg="contrib.startup.errors.temp_dir";
        System.err.println(Language.interpolate(arg,contrib.getName()));
      }
    }
 catch (    MalformedURLException e1) {
      System.err.println(Language.interpolate("contrib.import.errors.link",contrib.getName()));
    }
  }
  editor.getTextArea().setEditable(true);
  editor.statusEmpty();
  System.out.println(Language.text("contrib.import.progress.final_list"));
  for (  String l : installedLibList) {
    System.out.println("  * " + l);
  }
}
