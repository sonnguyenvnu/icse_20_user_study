private static String removeJavadoc(VisitorState state,Comment danglingJavadoc,SuggestedFix.Builder builder){
  int javadocStart=danglingJavadoc.getSourcePos(0);
  int javadocEnd=javadocStart + danglingJavadoc.getText().length();
  if (state.getSourceCode().charAt(javadocEnd) == '\n') {
    javadocEnd++;
  }
  builder.replace(javadocStart,javadocEnd,"");
  return danglingJavadoc.getText();
}
