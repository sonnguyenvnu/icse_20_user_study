private static int findLongestLineLength(String s){
  String[] lines=s.split("\n");
  int longestLength=0;
  for (  String line : lines) {
    int length=line.length();
    if (length > longestLength) {
      longestLength=length;
    }
  }
  return longestLength;
}
