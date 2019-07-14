static private String capitalizeFirst(String string){
  char[] stringArray=string.toCharArray();
  stringArray[0]=Character.toUpperCase(stringArray[0]);
  return new String(stringArray);
}
