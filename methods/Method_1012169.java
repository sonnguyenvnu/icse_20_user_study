@Override @NotNull public Collection<Group> group(@NotNull AbstractTreeNode parent,@NotNull final Collection<TreeElement> children){
  final Object element=parent.getValue();
  if (!(element instanceof MainNodeTreeElement)) {
    return Collections.emptyList();
  }
  Map<RelationDescriptor,List<TreeElement>> groups=new HashMap<>();
  for (  TreeElement te : children) {
    if (!(te instanceof AspectTreeElement)) {
      continue;
    }
    AspectTreeElement ate=(AspectTreeElement)te;
    RelationDescriptor d=ate.getAspectDescriptor();
    if (!groups.containsKey(d)) {
      groups.put(d,new ArrayList<>());
    }
    groups.get(d).add(ate);
  }
  Collection<Group> result=new ArrayList<>();
  for (  Entry<RelationDescriptor,List<TreeElement>> e : groups.entrySet()) {
    result.add(new AspectGroup(e.getKey(),e.getValue()));
  }
  return result;
}
