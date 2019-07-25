/** 
 * ?????tree???treeview??
 * @author fengshuonan
 * @Date 2018/12/23 4:57 PM
 */
@RequestMapping(value="/treeview") @ResponseBody public List<TreeviewNode> treeview(){
  List<TreeviewNode> treeviewNodes=this.deptService.treeviewNodes();
  DefaultTreeBuildFactory<TreeviewNode> factory=new DefaultTreeBuildFactory<>();
  factory.setRootParentId("0");
  List<TreeviewNode> results=factory.doTreeBuild(treeviewNodes);
  DeptTreeWrapper.clearNull(results);
  return results;
}
