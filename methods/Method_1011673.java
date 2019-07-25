public TreeBuilder property(@Nullable Icon icon,String name,Object value){
  TextTreeNode newChild=new TextTreeNode(propertyText(name,String.valueOf(value)));
  if (icon != null)   newChild.setIcon(icon);
  myRoot.add(newChild);
  return this;
}
