@Setup public void prepare(){
  for (int i=0, stringsLength=strings.length; i < stringsLength; i++) {
    strings[i]=new RandomString().randomAlphaNumeric(8);
  }
}
