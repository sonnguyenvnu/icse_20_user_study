String format(String raw,String lineFormat){
  Scanner scanner=new Scanner(raw);
  int firstImportLine=0;
  int lastImportLine=0;
  int line=0;
  boolean isMultiLineComment=false;
  List<String> imports=new ArrayList<>();
  while (scanner.hasNext()) {
    line++;
    String next=scanner.nextLine();
    if (next == null) {
      break;
    }
    isMultiLineComment|=next.contains("/*");
    if (isMultiLineComment && next.contains("*/")) {
      isMultiLineComment=false;
      if (!next.contains("/*")) {
        continue;
      }
    }
    if (next.startsWith("import ")) {
      int i=next.indexOf(".");
      if (isNotValidImport(i)) {
        continue;
      }
      if (firstImportLine == 0) {
        firstImportLine=line;
      }
      lastImportLine=line;
      int endIndex=next.indexOf(";");
      String imprt=next.substring(START_INDEX_OF_IMPORTS_PACKAGE_DECLARATION,endIndex != -1 ? endIndex : next.length());
      if (!isMultiLineComment && !imports.contains(imprt)) {
        imports.add(imprt);
      }
    }
    if (!isMultiLineComment && isBeginningOfScope(next)) {
      break;
    }
  }
  scanner.close();
  List<String> sortedImports=ImportSorterImpl.sort(imports,importsOrder,lineFormat);
  return applyImportsToDocument(raw,firstImportLine,lastImportLine,sortedImports);
}
