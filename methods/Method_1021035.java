public static void unzip(String zipFilePath) throws IOException {
  String targetPath=zipFilePath.substring(0,zipFilePath.lastIndexOf("."));
  unzip(zipFilePath,targetPath,true);
}
