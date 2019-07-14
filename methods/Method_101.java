public static ArrayList<String> getPerms(String str){
  if (str == null) {
    return null;
  }
  ArrayList<String> permutations=new ArrayList<String>();
  if (str.length() == 0) {
    permutations.add("");
    return permutations;
  }
  char first=str.charAt(0);
  String remainder=str.substring(1);
  ArrayList<String> words=getPerms(remainder);
  for (  String word : words) {
    for (int j=0; j <= word.length(); j++) {
      String s=insertCharAt(word,first,j);
      permutations.add(s);
    }
  }
  return permutations;
}
