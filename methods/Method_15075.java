protected void remove(int position,boolean destroy){
  if (fragments != null && position >= 0 && position < fragments.length && fragments[position] != null) {
    try {
      fragmentManager.beginTransaction().remove(fragments[position]).commit();
    }
 catch (    Exception e) {
      Log.e(TAG,"remove  try { fragmentManager.beginTransaction().remove(fragments[position]).commit();" + " } catch (Exception e) {\n" + e.getMessage());
      destroy=true;
    }
    if (destroy) {
      fragments[position].onDestroy();
      fragments[position]=null;
    }
  }
}
