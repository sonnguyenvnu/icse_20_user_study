private static String getQualifiedName(VisitorState state,SuggestedFix.Builder builder){
  Symbol sym=FindIdentifiers.findIdent("Nullable",state,KindSelector.VAL_TYP);
  String defaultType=state.isAndroidCompatible() ? "android.support.annotation.Nullable" : "javax.annotation.Nullable";
  if (sym != null) {
    ClassSymbol classSym=(ClassSymbol)sym;
    if (classSym.isAnnotationType()) {
      return "Nullable";
    }
 else {
      return defaultType;
    }
  }
  builder.addImport(defaultType);
  return "Nullable";
}
