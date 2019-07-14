void downloadAndUpdateContributionListing(Base base){
  final ContributionTab activeTab=getActiveTab();
  ContribProgressMonitor progress=new ContribProgressBar(activeTab.progressBar){
    @Override public void startTask(    String name,    int maxValue){
      super.startTask(name,maxValue);
      progressBar.setVisible(true);
      progressBar.setString(null);
    }
    @Override public void setProgress(    int value){
      super.setProgress(value);
      progressBar.setValue(value);
    }
    @Override public void finishedAction(){
      progressBar.setVisible(false);
      activeTab.updateContributionListing();
      activeTab.updateCategoryChooser();
      if (error) {
        exception.printStackTrace();
        makeAndShowTab(true,false);
      }
 else {
        makeAndShowTab(false,false);
      }
    }
  }
;
  activeTab.contribListing.downloadAvailableList(base,progress);
}
