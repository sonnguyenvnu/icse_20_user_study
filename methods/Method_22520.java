/** 
 * Non-blocking call to remove a contribution in a new thread.
 */
void removeContribution(final Base base,final ContribProgressMonitor pm,final StatusPanel status){
  new Thread(new Runnable(){
    public void run(){
      remove(base,pm,status,ContributionListing.getInstance());
    }
  }
,"Contribution Uninstaller").start();
}
