/** 
 * Build set of dependencies for the given module, grouped under a fake root container
 */
public DepLink build(SModule module){
  DepLink rv=new DepLink(module.getModuleReference(),DependencyUtil.Role.None,null);
  List<DepLink> queue=ListSequence.fromList(new LinkedList<DepLink>());
  ListSequence.fromList(queue).addElement(rv);
  Map<Dependency,DepLink> visited=MapSequence.fromMap(new HashMap<Dependency,DepLink>());
  while (ListSequence.fromList(queue).isNotEmpty()) {
    DepLink e=ListSequence.fromList(queue).removeElementAt(0);
    List<DepLink> dependencies=dependencies(e.role,e.module);
    for (    DepLink d : ListSequence.fromList(dependencies)) {
      d.myParent=e;
      ListSequence.fromList(e.children()).addElement(d);
      Dependency key=d.getRoleModuleKey();
      if (MapSequence.fromMap(visited).containsKey(key)) {
        d.setReused(MapSequence.fromMap(visited).get(key));
      }
 else {
        MapSequence.fromMap(visited).put(key,d);
        ListSequence.fromList(queue).addElement(d);
      }
    }
  }
  return rv;
}
