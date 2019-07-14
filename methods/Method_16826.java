public boolean isPalindrome(int x){
  if (x < 0 || (x != 0 && x % 10 == 0))   return false;
  int halfReverseX=0;
  while (x > halfReverseX) {
    halfReverseX=halfReverseX * 10 + x % 10;
    x/=10;
  }
  return halfReverseX == x || halfReverseX / 10 == x;
}
