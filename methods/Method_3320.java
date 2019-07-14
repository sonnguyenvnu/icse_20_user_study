public char[] generateParameter(Table table,int current){
  StringBuilder sb=new StringBuilder();
  int i=0;
  for (  String d : delimiterList) {
    sb.append(d);
    int[] offset=offsetList.get(i++);
    sb.append(table.get(current + offset[0],offset[1]));
  }
  char[] o=new char[sb.length()];
  sb.getChars(0,sb.length(),o,0);
  return o;
}
