void remove(final Base base,final ContribProgressMonitor pm,final StatusPanel status,final ContributionListing contribListing){
  pm.startTask("Removing",ContribProgressMonitor.UNKNOWN);
  boolean doBackup=Preferences.getBoolean("contribution.backup.on_remove");
  boolean success=false;
  if (getType() == ContributionType.MODE) {
    boolean isModeActive=false;
    ModeContribution m=(ModeContribution)this;
    Iterator<Editor> iter=base.getEditors().iterator();
    while (iter.hasNext()) {
      Editor e=iter.next();
      if (e.getMode().equals(m.getMode())) {
        isModeActive=true;
        break;
      }
    }
    if (!isModeActive) {
      m.clearClassLoader(base);
    }
 else {
      pm.cancel();
      Messages.showMessage("Mode Manager","Please save your Sketch and change the Mode of all Editor\n" + "windows that have " + name + " as the active Mode.");
      return;
    }
  }
  if (getType() == ContributionType.TOOL) {
    base.clearToolMenus();
    ((ToolContribution)this).clearClassLoader();
  }
  if (doBackup) {
    success=backup(true,status);
  }
 else {
    success=Util.removeDir(getFolder(),false);
  }
  if (success) {
    try {
      EventQueue.invokeAndWait(new Runnable(){
        @Override public void run(){
          Contribution advertisedVersion=contribListing.getAvailableContribution(LocalContribution.this);
          if (advertisedVersion == null) {
            contribListing.removeContribution(LocalContribution.this);
          }
 else {
            contribListing.replaceContribution(LocalContribution.this,advertisedVersion);
          }
          base.refreshContribs(LocalContribution.this.getType());
          base.setUpdatesAvailable(contribListing.countUpdates(base));
        }
      }
);
    }
 catch (    InterruptedException e) {
      e.printStackTrace();
    }
catch (    InvocationTargetException e) {
      Throwable cause=e.getCause();
      if (cause instanceof RuntimeException) {
        throw (RuntimeException)cause;
      }
 else {
        cause.printStackTrace();
      }
    }
  }
 else {
    if (!doBackup || (doBackup && backup(false,status))) {
      if (setDeletionFlag(true)) {
        try {
          EventQueue.invokeAndWait(new Runnable(){
            @Override public void run(){
              contribListing.replaceContribution(LocalContribution.this,LocalContribution.this);
              base.refreshContribs(LocalContribution.this.getType());
              base.setUpdatesAvailable(contribListing.countUpdates(base));
            }
          }
);
        }
 catch (        InterruptedException e) {
          e.printStackTrace();
        }
catch (        InvocationTargetException e) {
          Throwable cause=e.getCause();
          if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
          }
 else {
            cause.printStackTrace();
          }
        }
      }
    }
 else {
      status.setErrorMessage("Could not delete the contribution's files");
    }
  }
  if (success) {
    pm.finished();
  }
 else {
    pm.cancel();
  }
}
