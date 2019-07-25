public char calculate(){
  double average=0;
  for (  int score : testScores) {
    average+=score;
  }
  average/=testScores.length;
  if (average >= 90) {
    return 'O';
  }
 else   if (average >= 80) {
    return 'E';
  }
 else   if (average >= 70) {
    return 'A';
  }
 else   if (average >= 55) {
    return 'P';
  }
 else   if (average >= 40) {
    return 'D';
  }
 else {
    return 'T';
  }
}
