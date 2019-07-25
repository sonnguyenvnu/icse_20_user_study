/** 
 * ??????
 * @return
 */
@RequestMapping(params="depart") @ResponseBody public List<ComboBox> depart(HttpServletResponse response,HttpServletRequest request,ComboBox comboBox){
  String id=request.getParameter("id");
  List<ComboBox> comboBoxs=new ArrayList<ComboBox>();
  List<TSDepart> departs=new ArrayList();
  if (StringUtil.isNotEmpty(id)) {
    TSUser user=systemService.get(TSUser.class,id);
    List<TSDepart[]> resultList=systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.orgId and uo.id=?",id);
    for (    TSDepart[] departArr : resultList) {
      departs.add(departArr[0]);
    }
  }
  List<TSDepart> departList=systemService.getList(TSDepart.class);
  comboBoxs=TagUtil.getComboBox(departList,departs,comboBox);
  return comboBoxs;
}
