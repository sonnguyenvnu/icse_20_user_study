public static File makeCaptureImageOutputFile(){
  String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.US).format(new Date());
  String fileName="IMG_" + timestamp + ".jpg";
  return makeSaveImageOutputFile(fileName);
}
