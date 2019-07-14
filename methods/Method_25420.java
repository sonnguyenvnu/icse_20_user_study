private static String getPackageFullName(VisitorState state){
  JCCompilationUnit compilationUnit=(JCCompilationUnit)state.getPath().getCompilationUnit();
  return compilationUnit.packge.fullname.toString();
}
