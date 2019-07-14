private void findJavadocs(){
  Collection<JavadocElement> kids=new ArrayList<>();
  Matcher javadocTagMatcher=JAVADOC_TAG.matcher(getFilteredComment());
  while (javadocTagMatcher.find()) {
    JavadocTag tag=JavadocTag.tagFor(javadocTagMatcher.group(1));
    int tagStartIndex=javadocTagMatcher.start(1);
    if (tag != null) {
      kids.add(new JavadocElement(getBeginLine(),getBeginLine(),tagStartIndex,tagStartIndex + tag.label.length() + 1,tag));
    }
  }
  children=kids.toArray(new Node[0]);
}
