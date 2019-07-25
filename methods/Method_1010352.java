@Override public void dispose(){
  myClm.removeListener(myClassesListener);
  INSTANCE=null;
}
