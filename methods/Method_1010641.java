@Override public void dispose(){
  getTypechecking().dispose();
  myRequesting.clear();
  super.dispose();
}
