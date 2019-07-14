public static void walk(String folderPath,Handler handler){
  long start=System.currentTimeMillis();
  List<File> fileList=IOUtil.fileList(folderPath);
  int i=0;
  for (  File file : fileList) {
    System.out.print(file);
    Document document=convert2Document(file);
    System.out.println(" " + ++i + " / " + fileList.size());
    handler.handle(document);
  }
  System.out.printf("????%d ms\n",System.currentTimeMillis() - start);
}
