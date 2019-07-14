static public CellAtRow load(String s,Pool pool) throws Exception {
  int semicolon=s.indexOf(';');
  int row=Integer.parseInt(s.substring(0,semicolon));
  Cell cell=semicolon < s.length() - 1 ? Cell.loadStreaming(s.substring(semicolon + 1),pool) : null;
  return new CellAtRow(row,cell);
}
