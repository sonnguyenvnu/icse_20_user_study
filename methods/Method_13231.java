@Override @SuppressWarnings("unchecked") public <T extends DefaultMutableTreeNode & ContainerEntryGettable & UriGettable>T make(API api,Container.Entry entry){
  int lastSlashIndex=entry.getPath().lastIndexOf("/");
  String label=entry.getPath().substring(lastSlashIndex + 1);
  String location=new File(entry.getUri()).getPath();
  T node=(T)new TreeNode(entry,new TreeNodeBean(label,"Location: " + location,ICON));
  node.add(new DefaultMutableTreeNode());
  return node;
}
