public static float getImageRatio(String imageUrl){
  if (TextUtils.isEmpty(imageUrl))   return Float.NaN;
  try {
    Matcher matcher=REGEX_1.matcher(imageUrl);
    String widthStr;
    String heightStr;
    if (matcher.find()) {
      if (matcher.groupCount() >= 2) {
        widthStr=matcher.group(1);
        heightStr=matcher.group(2);
        if (widthStr.length() < 5 && heightStr.length() < 5) {
          int urlWidth=Integer.parseInt(widthStr);
          int urlHeight=Integer.parseInt(heightStr);
          if (urlWidth == 0 || urlHeight == 0) {
            return 1;
          }
          return (float)urlWidth / urlHeight;
        }
      }
    }
 else {
      matcher=REGEX_2.matcher(imageUrl);
      if (matcher.find()) {
        if (matcher.groupCount() >= 2) {
          widthStr=matcher.group(1);
          heightStr=matcher.group(2);
          if (widthStr.length() < 5 && heightStr.length() < 5) {
            int urlWidth=Integer.parseInt(widthStr);
            int urlHeight=Integer.parseInt(heightStr);
            if (urlWidth == 0 || urlHeight == 0) {
              return 1;
            }
            return (float)urlWidth / urlHeight;
          }
        }
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return Float.NaN;
}
