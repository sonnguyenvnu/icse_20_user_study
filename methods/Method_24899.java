protected int writeImportList(PrintWriter out,String[] imports){
  int count=0;
  if (imports != null && imports.length != 0) {
    for (    String item : imports) {
      out.println("import " + item + "; ");
      count++;
    }
    out.println();
    count++;
  }
  return count;
}
