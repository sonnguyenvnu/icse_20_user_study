public void replaceAll(String regex,String replacement){
  for (int col=0; col < columns.length; col++) {
    replaceAll(regex,replacement,col);
  }
}
