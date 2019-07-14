@Override @SuppressWarnings("unchecked") public <T extends DefaultMutableTreeNode & ContainerEntryGettable & UriGettable>T make(API api,Container.Entry entry){
  String location=new File(entry.getUri()).getPath();
  return (T)new TreeNode(entry,new TreeNodeBean("ejb-jar.xml","Location: " + location,ICON));
}
