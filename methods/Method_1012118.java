public void write(Element element,Project project) throws CantSaveSomethingException {
  writeOption(element,project,FINDERS,myFindersOptions);
  writeOption(element,project,SCOPE,myScopeOptions);
  writeOption(element,project,VIEW,myViewOptions);
}
