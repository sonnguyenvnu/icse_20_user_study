@Override public String saveStaticFile(InputStream fileStream,String fileName) throws IOException {
  try {
    String suffix=fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
    String filePath=DateFormatter.toString(new Date(),"yyyyMMdd");
    new File(getStaticFilePath() + "/" + filePath).mkdirs();
    String realFileName=System.nanoTime() + suffix;
    String fileAbsName=getStaticFilePath() + "/" + filePath + "/" + realFileName;
    try (FileOutputStream out=new FileOutputStream(fileAbsName)){
      StreamUtils.copy(fileStream,out);
    }
     return getStaticLocation() + filePath + "/" + realFileName;
  }
  finally {
    fileStream.close();
  }
}
