public static double[][] loadtxt(File matrixFile) throws Exception {
  double[][] matrix=null;
  BufferedReader in=Files.newBufferedReader(matrixFile.toPath(),Charset.defaultCharset());
  String line;
  ArrayList<String> lines=new ArrayList<String>();
  while ((line=in.readLine()) != null) {
    lines.add(line);
  }
  int numRows=lines.size();
  matrix=new double[numRows][];
  for (int row=0; row < numRows; row++) {
    String[] fields=lines.get(row).split(" ");
    matrix[row]=new double[fields.length];
    for (int col=0; col < fields.length; col++) {
      matrix[row][col]=Double.parseDouble(fields[col]);
    }
  }
  return matrix;
}
