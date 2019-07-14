public static int compute(char[] str1,char[] str2){
  int size1=str1.length;
  int size2=str2.length;
  if (size1 == 0 || size2 == 0)   return 0;
  int longest=0;
  for (int i=0; i < size1; ++i) {
    int m=i;
    int n=0;
    int length=0;
    while (m < size1 && n < size2) {
      if (str1[m] != str2[n]) {
        length=0;
      }
 else {
        ++length;
        if (longest < length) {
          longest=length;
        }
      }
      ++m;
      ++n;
    }
  }
  for (int j=1; j < size2; ++j) {
    int m=0;
    int n=j;
    int length=0;
    while (m < size1 && n < size2) {
      if (str1[m] != str2[n]) {
        length=0;
      }
 else {
        ++length;
        if (longest < length) {
          longest=length;
        }
      }
      ++m;
      ++n;
    }
  }
  return longest;
}
