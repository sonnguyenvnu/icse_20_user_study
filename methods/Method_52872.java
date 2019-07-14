private boolean isUnbalanced(String image,char pattern){
  char[] array=image.toCharArray();
  boolean foundPattern=false;
  for (int i=array.length - 1; i > 0; i--) {
    if (array[i] == pattern) {
      foundPattern=true;
    }
    if (array[i] == ';') {
      return foundPattern;
    }
  }
  return foundPattern;
}
