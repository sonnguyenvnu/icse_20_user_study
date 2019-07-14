private void notifyOfHistoryChanges(){
  AndroidUtilities.runOnUIThread(new Runnable(){
    @Override public void run(){
      if (delegate != null) {
        delegate.historyChanged();
      }
    }
  }
);
}
