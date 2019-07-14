@Override public List<String> getChildrenKeys(final String key){
  try {
    List<String> result=client.getChildren().forPath(key);
    Collections.sort(result,new Comparator<String>(){
      @Override public int compare(      final String o1,      final String o2){
        return o2.compareTo(o1);
      }
    }
);
    return result;
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
    return Collections.emptyList();
  }
}
