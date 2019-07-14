public static final String getPictureName(){
  return RxTimeTool.getCurrentDateTime(DATE_FORMAT_LINK) + "_" + new Random().nextInt(1000) + ".jpg";
}
