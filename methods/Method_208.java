public static int findUniquePalindromes(List<String> allSubstrings){
  int totalUniquePalindromes=0;
  for (  String s : allSubstrings) {
    int left=0;
    int right=s.length() - 1;
    boolean isPalindrome=true;
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        isPalindrome=false;
        break;
      }
      left++;
      right--;
    }
    if (isPalindrome) {
      totalUniquePalindromes++;
    }
  }
  return totalUniquePalindromes;
}
