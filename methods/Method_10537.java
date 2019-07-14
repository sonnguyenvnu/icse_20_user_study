private int getMax(double currentProgress){
  if (currentProgress < 100 && currentProgress > 0) {
    return 100;
  }
 else   if (currentProgress >= 100 && currentProgress < 1000) {
    return 1000;
  }
 else   if (currentProgress >= 1000 && currentProgress < 5000) {
    return 5000;
  }
 else   if (currentProgress >= 5000 && currentProgress < 20000) {
    return 20000;
  }
 else   if (currentProgress >= 20000 && currentProgress < 100000) {
    return 100000;
  }
 else   if (currentProgress >= 100000) {
    return RxDataTool.stringToInt(currentProgress * 1.1 + "");
  }
 else {
    return RxDataTool.stringToInt(currentProgress + "");
  }
}
