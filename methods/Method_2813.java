public static LinkedList<String> readLineList(String path){
  LinkedList<String> result=new LinkedList<String>();
  String txt=readTxt(path);
  if (txt == null)   return result;
  StringTokenizer tokenizer=new StringTokenizer(txt,"\n");
  while (tokenizer.hasMoreTokens()) {
    result.add(tokenizer.nextToken());
  }
  return result;
}
