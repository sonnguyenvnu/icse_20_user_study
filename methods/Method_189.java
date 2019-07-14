private String helper(int num){
  if (num == 0) {
    return "";
  }
 else   if (num < 20) {
    return LESS_THAN_20[num] + " ";
  }
 else   if (num < 100) {
    return TENS[num / 10] + " " + helper(num % 10);
  }
 else {
    return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
  }
}
