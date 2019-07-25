public void _put(String fqn,String key,Object value){
  Node n;
  StringHolder child_name=new StringHolder();
  boolean child_exists=false;
  if (fqn == null || key == null || value == null)   return;
  n=findParentNode(fqn,child_name,true);
  if (child_name.getValue() != null) {
    child_exists=n.childExists(child_name.getValue());
    n.createChild(child_name.getValue(),fqn,n,key,value);
  }
 else {
    child_exists=true;
    n.setData(key,value);
  }
  if (child_exists)   notifyNodeModified(fqn);
 else   notifyNodeAdded(fqn);
}
