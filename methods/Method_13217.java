protected static boolean isAEjbModule(Container.Entry entry){
  Collection<Container.Entry> children=entry.getChildren();
  if (children != null) {
    Container.Entry metaInf=null;
    for (    Container.Entry child : children) {
      if (child.getPath().equals("META-INF")) {
        metaInf=child;
        break;
      }
    }
    if (metaInf != null) {
      children=metaInf.getChildren();
      for (      Container.Entry child : children) {
        if (child.getPath().equals("META-INF/ejb-jar.xml")) {
          return true;
        }
      }
    }
  }
  return false;
}
