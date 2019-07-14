protected int getLimit(){
  Object v=ProjectManager.singleton.getPreferenceStore().get("ui.browsing.listFacet.limit");
  if (v != null) {
    if (v instanceof Number) {
      return ((Number)v).intValue();
    }
 else {
      try {
        int n=Integer.parseInt(v.toString());
        return n;
      }
 catch (      NumberFormatException e) {
      }
    }
  }
  return 2000;
}
