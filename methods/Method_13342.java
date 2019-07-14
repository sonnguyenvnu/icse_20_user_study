public static Container.Entry recursiveSearchEntry(Container.Entry parent,String path){
  Container.Entry entry=null;
  for (  Container.Entry child : parent.getChildren()) {
    if (path.equals(child.getPath())) {
      entry=child;
      break;
    }
  }
  if (entry != null) {
    return entry;
  }
 else {
    for (    Container.Entry child : parent.getChildren()) {
      if (path.startsWith(child.getPath() + '/')) {
        entry=child;
        break;
      }
    }
    return (entry != null) ? searchEntry(entry,path) : null;
  }
}
