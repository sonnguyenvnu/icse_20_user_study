@NotNull public static Element serialize(@NotNull DebugInfo debugInfo){
  Element top=new Element(ELEMENT_DEBUG_INFO);
  top.setAttribute(ATTR_VER,CURRENT_VERSION);
  DebugInfoRoot[] roots=sortedRoots(debugInfo);
  TreeSet<SAbstractConcept> allConcepts=new TreeSet<>(Comparator.comparing(SAbstractConcept::getQualifiedName));
  collectAllConcepts(roots,allConcepts);
  int i=0;
  HashMap<SAbstractConcept,Integer> conceptsOrder=new HashMap<>();
  final PersistenceFacade pf=PersistenceFacade.getInstance();
  for (  SAbstractConcept concept : allConcepts) {
    conceptsOrder.put(concept,i++);
    top.addContent(new Element(CONCEPT).setAttribute(ATTR_FQN,pf.asString(concept)));
  }
  for (  DebugInfoRoot dr : roots) {
    Element r=new Element(ELEMENT_ROOT);
    top.addContent(r);
    SNodeReference nr=dr.getNodeRef();
    if (nr != null) {
      r.setAttribute(ATTR_NODE_REF,nr.toString());
    }
    r.addContent(serialize(dr,conceptsOrder));
  }
  return top;
}
