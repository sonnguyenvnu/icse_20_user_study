public void _put(String fqn,HashMap data){
  Node n;
  StringHolder child_name=new StringHolder();
  boolean child_exists=false;
  if (fqn == null)   return;
  n=findParentNode(fqn,child_name,true);
  if (child_name.getValue() != null) {
    child_exists=n.childExists(child_name.getValue());
    n.createChild(child_name.getValue(),fqn,n,data);
  }
 else {
    child_exists=true;
    n.setData(data);
  }
  if (child_exists)   notifyNodeModified(fqn);
 else   notifyNodeAdded(fqn);
}
