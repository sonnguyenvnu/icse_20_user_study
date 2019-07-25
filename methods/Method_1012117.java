public void read(Element element,Project project) throws CantLoadSomethingException {
  Element optionXML;
  optionXML=element.getChild(FINDERS);
  if (optionXML == null)   throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
  myFindersOptions=new FindersOptions(optionXML,project);
  optionXML=element.getChild(SCOPE);
  if (optionXML == null)   throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
  myScopeOptions=new ScopeOptions(optionXML,project);
  optionXML=element.getChild(VIEW);
  if (optionXML == null)   throw new CantLoadSomethingException("Tag " + FINDERS + " not found");
  myViewOptions=new ViewOptions(optionXML,project);
}
