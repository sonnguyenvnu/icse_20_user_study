public void dispose(){
  for (  BasePrefsPage page : myPages) {
    page.unregister();
  }
  myPages.clear();
}
