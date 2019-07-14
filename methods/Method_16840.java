public boolean isMatch(String s,String p){
  if (p.length() == 0)   return s.length() == 0;
  int sL=s.length(), pL=p.length();
  boolean[][] dp=new boolean[sL + 1][pL + 1];
  char[] sc=s.toCharArray(), pc=p.toCharArray();
  dp[0][0]=true;
  for (int i=2; i <= pL; ++i) {
    if (pc[i - 1] == '*' && dp[0][i - 2]) {
      dp[0][i]=true;
    }
  }
  for (int i=1; i <= sL; ++i) {
    for (int j=1; j <= pL; ++j) {
      if (pc[j - 1] == '.' || pc[j - 1] == sc[i - 1]) {
        dp[i][j]=dp[i - 1][j - 1];
      }
      if (pc[j - 1] == '*') {
        if (pc[j - 2] == sc[i - 1] || pc[j - 2] == '.') {
          dp[i][j]=dp[i - 1][j] || dp[i][j - 2];
        }
 else {
          dp[i][j]=dp[i][j - 2];
        }
      }
    }
  }
  return dp[sL][pL];
}
