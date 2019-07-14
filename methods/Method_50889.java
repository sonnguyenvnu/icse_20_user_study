private static void addFilesFromFilelist(String inputFilePath,CPD cpd,boolean recursive){
  File file=new File(inputFilePath);
  List<File> files=new ArrayList<>();
  try {
    if (!file.exists()) {
      throw new FileNotFoundException("Couldn't find directory/file '" + inputFilePath + "'");
    }
 else {
      String filePaths=FileUtil.readFilelist(new File(inputFilePath));
      for (      String param : filePaths.split(",")) {
        File fileToAdd=new File(param);
        if (!fileToAdd.exists()) {
          throw new FileNotFoundException("Couldn't find directory/file '" + param + "'");
        }
        files.add(fileToAdd);
      }
      addSourcesFilesToCPD(files,cpd,recursive);
    }
  }
 catch (  IOException ex) {
    throw new IllegalStateException(ex);
  }
}
