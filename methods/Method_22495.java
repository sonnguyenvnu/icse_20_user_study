private void installContribution(AvailableContribution ad,String url){
  installRemoveButton.setEnabled(false);
  try {
    URL downloadUrl=new URL(url);
    installProgressBar.setVisible(true);
    ContribProgressBar downloadProgress=new ContribProgressBar(installProgressBar){
      public void finishedAction(){
      }
      public void cancelAction(){
        finishInstall(false);
      }
    }
;
    ContribProgressBar installProgress=new ContribProgressBar(installProgressBar){
      public void finishedAction(){
        finishInstall(isError());
      }
      public void cancelAction(){
        finishedAction();
      }
    }
;
    ContributionManager.downloadAndInstall(getBase(),downloadUrl,ad,downloadProgress,installProgress,getStatusPanel());
  }
 catch (  MalformedURLException e) {
    Messages.showWarning(Language.text("contrib.errors.install_failed"),Language.text("contrib.errors.malformed_url"),e);
  }
}
