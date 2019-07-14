protected void writeTriangle(){
  writer.println("0");
  writer.println("3DFACE");
  writer.println("8");
  writer.println(String.valueOf(currentLayer));
  write("10",vertices[0][X]);
  write("20",vertices[0][Y]);
  write("30",vertices[0][Z]);
  write("11",vertices[1][X]);
  write("21",vertices[1][Y]);
  write("31",vertices[1][Z]);
  write("12",vertices[2][X]);
  write("22",vertices[2][Y]);
  write("32",vertices[2][Z]);
  write("13",vertices[2][X] + EPSILON);
  write("23",vertices[2][Y] + EPSILON);
  write("33",vertices[2][Z] + EPSILON);
  vertexCount=0;
}
