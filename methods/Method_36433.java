public void decode(Object instance,Node base,Document document,List<String> filters) throws Exception {
  Node node=base;
  String name=path.path;
  if (sorter != null) {
    deSorter=sorter;
  }
  if (deSorter != null) {
    Collections.sort(members,deSorter);
    deSorter=null;
  }
  if (name != null && name.length() > 0) {
    Element element=document.createElement(name);
    node=node.appendChild(element);
  }
  for (  XAnnotatedMember annotatedMember : members) {
    annotatedMember.decode(instance,node,document,filters);
  }
}
