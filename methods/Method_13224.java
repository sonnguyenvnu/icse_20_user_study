@Override @SuppressWarnings("unchecked") public <T extends DefaultMutableTreeNode & ContainerEntryGettable & UriGettable>T make(API api,Container.Entry entry){
  int lastSlashIndex=entry.getPath().lastIndexOf("/");
  Collection<Container.Entry> entries=entry.getChildren();
  while (entries.size() == 1) {
    Container.Entry child=entries.iterator().next();
    if (!child.isDirectory() || (api.getTreeNodeFactory(child) != this) || (entry.getContainer() != child.getContainer()))     break;
    entry=child;
    entries=entry.getChildren();
  }
  String label=entry.getPath().substring(lastSlashIndex + 1).replace("/",".");
  String location=new File(entry.getUri()).getPath();
  T node=(T)new TreeNode(entry,new TreeNodeBean(label,"Location: " + location,getIcon(),getOpenIcon()));
  if (entries.size() > 0) {
    node.add(new DefaultMutableTreeNode());
  }
  return node;
}
