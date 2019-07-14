public static LinkedList<String[]> readCsv(String path){
  LinkedList<String[]> resultList=new LinkedList<String[]>();
  LinkedList<String> lineList=readLineList(path);
  for (  String line : lineList) {
    resultList.add(line.split(","));
  }
  return resultList;
}
