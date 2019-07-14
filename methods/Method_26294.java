@Override protected void processSuppressWarningsValues(List<String> values){
  for (int i=0; i < values.size(); i++) {
    if (values.get(i).equals("deprecated")) {
      values.set(i,"deprecation");
    }
  }
}
