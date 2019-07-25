/** 
 * ??????
 * @return
 */
@RequestMapping(params="del") @ResponseBody public AjaxJson del(TSCategoryEntity tSCategory,HttpServletRequest request){
  AjaxJson j=new AjaxJson();
  tSCategory=systemService.getEntity(TSCategoryEntity.class,tSCategory.getId());
  j.setMsg("????????");
  categoryService.delete(tSCategory);
  systemService.addLog(j.getMsg(),Globals.Log_Type_DEL,Globals.Log_Leavel_INFO);
  return j;
}
