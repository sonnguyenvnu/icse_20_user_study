public static String fixFileName(String fileName){
  if (fileName != null) {
    fileName=fileName.replaceAll("[\u0001-\u001f<>:\"/\\\\|?*\u007f]+","").trim();
  }
  return fileName;
}
