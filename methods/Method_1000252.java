private void solve(String s,boolean isLeft){
  String num="";
  int xSum=0;
  int rest=0;
  boolean isNeg=false;
  for (int i=0; i < s.length(); i++) {
    char c=s.charAt(i);
    if (c == '-') {
      if (!num.isEmpty()) {
        xSum=calculate(num,isNeg,xSum,rest)[0];
        rest=calculate(num,isNeg,xSum,rest)[1];
      }
      isNeg=true;
      num="";
    }
 else     if (c == '+') {
      if (!num.isEmpty()) {
        xSum=calculate(num,isNeg,xSum,rest)[0];
        rest=calculate(num,isNeg,xSum,rest)[1];
      }
      isNeg=false;
      num="";
    }
 else {
      num+=c;
    }
  }
  if (!num.isEmpty()) {
    xSum=calculate(num,isNeg,xSum,rest)[0];
    rest=calculate(num,isNeg,xSum,rest)[1];
  }
  if (isLeft) {
    xL=xSum;
    tL=rest;
  }
 else {
    xR=xSum;
    tR=rest;
  }
}
