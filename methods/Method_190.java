public boolean judgeCircle(String moves){
  int UD=0;
  int LR=0;
  for (int i=0; i < moves.length(); i++) {
    if (moves.charAt(i) == 'U') {
      UD++;
    }
 else     if (moves.charAt(i) == 'D') {
      UD--;
    }
 else     if (moves.charAt(i) == 'L') {
      LR++;
    }
 else     if (moves.charAt(i) == 'R') {
      LR--;
    }
  }
  return UD == 0 && LR == 0;
}
