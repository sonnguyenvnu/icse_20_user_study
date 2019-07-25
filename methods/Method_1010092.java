@NotNull public static DebugInfo restore(@NotNull Element top){
  if (!ELEMENT_DEBUG_INFO.equals(top.getName())) {
    return new DebugInfo();
  }
  if (!CURRENT_VERSION.equals(top.getAttributeValue(ATTR_VER))) {
    return new DebugInfo();
  }
  DebugInfo rv=new DebugInfo();
  int i=0;
  HashMap<Integer,SAbstractConcept> conceptsOrder=new HashMap<>();
  final PersistenceFacade pf=PersistenceFacade.getInstance();
  for (  Element c : top.getChildren(CONCEPT)) {
    conceptsOrder.put(i++,pf.createConcept(c.getAttributeValue(ATTR_FQN)));
  }
  for (  Element r : top.getChildren(ELEMENT_ROOT)) {
    String nr=r.getAttributeValue(ATTR_NODE_REF);
    DebugInfoRoot dr=new DebugInfoRoot(nr == null ? null : SNodePointer.deserialize(nr));
    rv.putRootInfo(dr);
    for (    Element f : r.getChildren(ELEMENT_FILE)) {
      String filename=f.getAttributeValue(ATTR_NAME);
      for (      Element e : f.getChildren(ELEMENT_NODE_INFO)) {
        TraceablePositionInfo pi=new TraceablePositionInfo();
        pi.setFileName(filename);
        restore(e,pi);
        String conceptAttr=e.getAttributeValue(CONCEPT);
        SAbstractConcept conc=null;
        if (conceptAttr != null) {
          conc=conceptsOrder.get(Integer.parseInt(conceptAttr));
        }
        pi.setConcept(conc);
        pi.setPropertyString(e.getAttributeValue(ATTR_TRACEABLE_PROP));
        dr.addPosition(pi);
      }
      for (      Element e : f.getChildren(ELEMENT_SCOPE_INFO)) {
        ScopePositionInfo pi=new ScopePositionInfo();
        pi.setFileName(filename);
        restore(e,pi);
        for (        Element varInfo : e.getChildren(ELEMENT_VAR_INFO)) {
          VarInfo vi=new VarInfo();
          vi.setVarName(varInfo.getAttributeValue(ATTR_NAME));
          vi.setNodeId(varInfo.getAttributeValue(ATTR_NODE_ID));
          pi.addVarInfo(vi);
        }
        dr.addScopePosition(pi);
      }
      for (      Element e : f.getChildren(ELEMENT_UNIT_INFO)) {
        UnitPositionInfo pi=new UnitPositionInfo();
        pi.setFileName(filename);
        restore(e,pi);
        pi.setUnitName(e.getAttributeValue(ATTR_NAME));
        dr.addUnitPosition(pi);
      }
    }
  }
  return rv;
}
