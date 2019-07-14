private static int checksum(String s){
  int val=0;
  char[] chars=s.toCharArray();
  final int[] weights={7,3,1};
  for (int i=0; i < chars.length; i++) {
    int charVal=0;
    if (chars[i] >= '0' && chars[i] <= '9') {
      charVal=chars[i] - '0';
    }
 else     if (chars[i] >= 'A' && chars[i] <= 'Z') {
      charVal=chars[i] - 'A' + 10;
    }
    val+=charVal * weights[i % weights.length];
  }
  return val % 10;
}
