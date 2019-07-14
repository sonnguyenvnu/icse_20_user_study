protected String[] appendSelectors(String selector){
  if (externalSelectors == null) {
    return new String[]{selector};
  }
 else {
    int size=externalSelectors.size();
    String[] array=new String[size + 1];
    externalSelectors.toArray(array);
    array[size]=selector;
    return array;
  }
}
