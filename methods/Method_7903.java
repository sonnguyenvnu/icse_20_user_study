@Override public boolean onBackPressed(){
  for (int a=0; a < views.length; a++) {
    if (views[a] != null) {
      views[a].onDestroyActivity();
    }
  }
  return true;
}
