protected void writeLine(int index1,int index2){
  writer.println("0");
  writer.println("LINE");
  writer.println("8");
  writer.println(String.valueOf(currentLayer));
  write("10",vertices[index1][X]);
  write("20",vertices[index1][Y]);
  write("30",vertices[index1][Z]);
  write("11",vertices[index2][X]);
  write("21",vertices[index2][Y]);
  write("31",vertices[index2][Z]);
}
