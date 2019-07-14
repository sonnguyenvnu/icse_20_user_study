public void showFrame(ContributionType contributionType){
  ContributionTab showTab=getTab(contributionType);
  if (frame == null) {
    makeFrame();
    tabs.setPanel(showTab);
    downloadAndUpdateContributionListing(base);
  }
 else {
    tabs.setPanel(showTab);
  }
  frame.setVisible(true);
  tabs.requestFocusInWindow();
}
