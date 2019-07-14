public void invalidateContent(){
  try {
    this.setContent("");
  }
  finally {
    this.isContentValid=false;
    this.isNavigationLinksValid=false;
  }
}
