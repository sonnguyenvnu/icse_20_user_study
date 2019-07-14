public String numberToWords(int num){
  if (num == 0) {
    return "Zero";
  }
  int i=0;
  String words="";
  while (num > 0) {
    if (num % 1000 != 0) {
      words=helper(num % 1000) + THOUSANDS[i] + " " + words;
    }
    num/=1000;
    i++;
  }
  return words.trim();
}
