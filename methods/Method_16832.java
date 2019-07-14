public int[] plusOne(int[] digits){
  int p=digits.length - 1;
  if (digits[p] < 9) {
    digits[p]=++digits[p];
  }
 else {
    do {
      digits[p--]=0;
    }
 while (p >= 0 && digits[p] == 9);
    if (digits[0] != 0) {
      ++digits[p];
    }
 else {
      digits=new int[digits.length + 1];
      digits[0]=1;
    }
  }
  return digits;
}
