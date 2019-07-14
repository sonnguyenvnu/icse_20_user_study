private static int updateScoreForMatch(int currentScore,String target,String actual,int score){
  if (target.isEmpty() || currentScore == -1) {
    return currentScore;
  }
  return target.equals(actual) ? currentScore + score : -1;
}
