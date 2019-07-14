/** 
 * ??????????????????
 */
private int getRandom(int type){
  String saveWhere=null;
  int urlLength=0;
  if (type == 1) {
    saveWhere=HOME_ONE;
    urlLength=ConstantsImageUrl.HOME_ONE_URLS.length;
  }
 else   if (type == 2) {
    saveWhere=HOME_TWO;
    urlLength=ConstantsImageUrl.HOME_TWO_URLS.length;
  }
 else   if (type == 3) {
    saveWhere=HOME_SIX;
    urlLength=ConstantsImageUrl.HOME_SIX_URLS.length;
  }
  String homeSix=SPUtils.getString(saveWhere,"");
  if (!TextUtils.isEmpty(homeSix)) {
    String[] split=homeSix.split(",");
    Random random=new Random();
    for (int j=0; j < urlLength; j++) {
      int randomInt=random.nextInt(urlLength);
      boolean isUse=false;
      for (      String aSplit : split) {
        if (!TextUtils.isEmpty(aSplit) && String.valueOf(randomInt).equals(aSplit)) {
          isUse=true;
          break;
        }
      }
      if (!isUse) {
        StringBuilder sb=new StringBuilder(homeSix);
        sb.insert(0,randomInt + ",");
        SPUtils.putString(saveWhere,sb.toString());
        return randomInt;
      }
    }
  }
 else {
    Random random=new Random();
    int randomInt=random.nextInt(urlLength);
    SPUtils.putString(saveWhere,randomInt + ",");
    return randomInt;
  }
  return 0;
}
