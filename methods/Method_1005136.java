@SuppressWarnings("unchecked") public ComboTree tree(TSDepart depart,boolean recursive){
  ComboTree tree=new ComboTree();
  tree.setId(oConvertUtils.getString(depart.getId()));
  tree.setText(depart.getDepartname());
  List<TSDepart> departsList=findByProperty(TSDepart.class,"TSPDepart.id",depart.getId());
  if (departsList != null && departsList.size() > 0) {
    tree.setState("closed");
    tree.setChecked(false);
    if (recursive) {
      List<TSDepart> departList=new ArrayList<TSDepart>(departsList);
      List<ComboTree> children=new ArrayList<ComboTree>();
      for (      TSDepart d : departList) {
        ComboTree t=tree(d,true);
        children.add(t);
      }
      tree.setChildren(children);
    }
  }
  return tree;
}
