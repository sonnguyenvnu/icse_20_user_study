private static List<Element> serialize(DebugInfoRoot debugRoot,HashMap<SAbstractConcept,Integer> conceptsOrder){
  MultiMap<String,TraceablePositionInfo> p1=new MultiMap<>();
  MultiMap<String,ScopePositionInfo> p2=new MultiMap<>();
  MultiMap<String,UnitPositionInfo> p3=new MultiMap<>();
  for (  TraceablePositionInfo pi : debugRoot.getPositions()) {
    p1.putValue(pi.getFileName(),pi);
  }
  for (  ScopePositionInfo pi : debugRoot.getScopePositions()) {
    p2.putValue(pi.getFileName(),pi);
  }
  for (  UnitPositionInfo pi : debugRoot.getUnitPositions()) {
    p3.putValue(pi.getFileName(),pi);
  }
  HashSet<String> allFiles=new HashSet<>();
  allFiles.addAll(p1.keySet());
  allFiles.addAll(p2.keySet());
  allFiles.addAll(p3.keySet());
  final String[] allFilesSorted=allFiles.toArray(new String[0]);
  Arrays.sort(allFilesSorted);
  ArrayList<Element> rv=new ArrayList<>(allFilesSorted.length);
  for (  String filename : allFilesSorted) {
    Element fileElement=new Element(ELEMENT_FILE);
    fileElement.setAttribute(ATTR_NAME,filename);
    rv.add(fileElement);
    for (    TraceablePositionInfo pi : p1.get(filename)) {
      Element e=new Element(ELEMENT_NODE_INFO);
      fileElement.addContent(e);
      serialize(pi,e);
      if (pi.getConcept() != null) {
        assert conceptsOrder.containsKey(pi.getConcept());
        e.setAttribute(CONCEPT,conceptsOrder.get(pi.getConcept()).toString());
      }
      if (pi.getPropertyString() != null) {
        e.setAttribute(ATTR_TRACEABLE_PROP,pi.getPropertyString());
      }
    }
    for (    ScopePositionInfo pi : p2.get(filename)) {
      Element e=new Element(ELEMENT_SCOPE_INFO);
      fileElement.addContent(e);
      serialize(pi,e);
      for (      String varName : pi.getVarNames()) {
        Element var=new Element(ELEMENT_VAR_INFO);
        var.setAttribute(ATTR_NAME,varName);
        var.setAttribute(ATTR_NODE_ID,pi.getVarId(varName));
        e.addContent(var);
      }
    }
    for (    UnitPositionInfo pi : p3.get(filename)) {
      Element e=new Element(ELEMENT_UNIT_INFO);
      fileElement.addContent(e);
      serialize(pi,e);
      if (pi.getUnitName() != null) {
        e.setAttribute(ATTR_NAME,pi.getUnitName());
      }
    }
  }
  return rv;
}
