@Override public void write(Element element,Project project) throws CantSaveSomethingException {
  super.write(element,project);
  element.setAttribute(CATEGORY,myCategory);
  if (myCategoryKindName != null) {
    element.setAttribute(CATEGORY_KIND,myCategoryKindName);
  }
}
