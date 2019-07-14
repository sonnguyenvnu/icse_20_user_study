/** 
 * ????????????????
 * @param folderPath ??
 * @param verbose
 * @return
 */
public static List<Document> convert2DocumentList(String folderPath,boolean verbose){
  long start=System.currentTimeMillis();
  List<File> fileList=IOUtil.fileList(folderPath);
  List<Document> documentList=new LinkedList<Document>();
  int i=0;
  for (  File file : fileList) {
    if (verbose)     System.out.print(file);
    Document document=convert2Document(file);
    documentList.add(document);
    if (verbose)     System.out.println(" " + ++i + " / " + fileList.size());
  }
  if (verbose) {
    System.out.println(documentList.size());
    System.out.printf("????%d ms\n",System.currentTimeMillis() - start);
  }
  return documentList;
}
