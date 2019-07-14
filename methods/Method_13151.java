protected String[] appendSelectors(String... selectors){
  if (externalSelectors == null) {
    return selectors;
  }
 else {
    int size=externalSelectors.size();
    String[] array=new String[size + selectors.length];
    externalSelectors.toArray(array);
    System.arraycopy(selectors,0,array,size,selectors.length);
    return array;
  }
}
