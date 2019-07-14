/** 
 */
@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET}) public String list(RpSettRecord rpSettRecord,PageParam pageParam,Model model){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("userName",rpSettRecord.getUserName());
  PageBean pageBean=rpSettQueryService.querySettRecordListPage(pageParam,paramMap);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("rpSettRecord",rpSettRecord);
  return "sett/list";
}
