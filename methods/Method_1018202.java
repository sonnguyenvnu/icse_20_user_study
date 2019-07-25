public static void main(String args[]){
  List<String> files=new ArrayList<>();
  files.add("file://test.csv");
  files.add("file://test2.csv");
  String filePaths="\"" + StringUtils.join(files,"\",\"") + "\"";
  files.remove(0);
  String filePaths2=StringUtils.join(files,"\",\"");
  int i=0;
}
