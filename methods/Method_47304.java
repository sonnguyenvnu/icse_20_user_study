public synchronized void setListfiles(ArrayList<String> listfiles){
  if (listfiles != null) {
    for (    String gridfile : listfiles) {
      setPathAsGridOrList(gridfile,LIST);
    }
  }
}
