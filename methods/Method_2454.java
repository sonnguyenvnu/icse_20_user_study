public static int compute(char[] str1,char[] str2){
  int substringLength1=str1.length;
  int substringLength2=str2.length;
  int[][] opt=new int[substringLength1 + 1][substringLength2 + 1];
  for (int i=substringLength1 - 1; i >= 0; i--) {
    for (int j=substringLength2 - 1; j >= 0; j--) {
      if (str1[i] == str2[j])       opt[i][j]=opt[i + 1][j + 1] + 1;
 else       opt[i][j]=Math.max(opt[i + 1][j],opt[i][j + 1]);
    }
  }
  return opt[0][0];
}
