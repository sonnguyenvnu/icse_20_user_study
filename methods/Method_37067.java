public static int parseSize(String sourceValue,int defaultValue){
  int result;
  if (sourceValue != null && sourceValue.length() > 0) {
    sourceValue=sourceValue.trim();
    if (sourceValue.endsWith(RP)) {
      sourceValue=sourceValue.substring(0,sourceValue.length() - 2).trim();
      try {
        double number=Double.parseDouble(sourceValue);
        result=rp2px(number);
      }
 catch (      NumberFormatException e) {
        result=defaultValue;
      }
    }
 else {
      try {
        double number=Double.parseDouble(sourceValue);
        result=dp2px(number);
      }
 catch (      NumberFormatException e) {
        result=defaultValue;
      }
    }
  }
 else {
    result=defaultValue;
  }
  return result;
}
