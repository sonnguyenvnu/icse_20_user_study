public static List<Edit> insertImports(List<ImportStatement> imports){
  List<Edit> result=new ArrayList<>();
  for (  ImportStatement imp : imports) {
    result.add(Edit.insert(0,imp.getFullSourceLine() + "\n"));
  }
  return result;
}
