@Override public StateMachineModel<String,String> build(){
  Model model=null;
  Holder holder=null;
  org.eclipse.emf.ecore.resource.Resource resource=null;
  try {
    holder=getResourceUri(resolveResource());
    resource=UmlUtils.getResource(holder.uri.getPath());
    model=(Model)EcoreUtil.getObjectByType(resource.getContents(),UMLPackage.Literals.MODEL);
  }
 catch (  IOException e) {
    throw new IllegalArgumentException("Cannot build build model from resource " + resource + " or location " + location,e);
  }
 finally {
    if (holder != null && holder.path != null) {
      try {
        Files.deleteIfExists(holder.path);
      }
 catch (      Exception e2) {
      }
    }
  }
  UmlModelParser parser=new UmlModelParser(model,this);
  DataHolder dataHolder=parser.parseModel();
  if (resource != null) {
    try {
      resource.unload();
    }
 catch (    Exception e) {
    }
  }
  if (holder != null && holder.path != null) {
    try {
      Files.deleteIfExists(holder.path);
    }
 catch (    Exception e2) {
    }
  }
  return new DefaultStateMachineModel<String,String>(null,dataHolder.getStatesData(),dataHolder.getTransitionsData());
}
