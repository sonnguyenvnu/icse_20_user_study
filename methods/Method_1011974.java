public void init(){
  myPages=createPages();
  for (  BasePrefsPage page : myPages) {
    page.register();
  }
}
