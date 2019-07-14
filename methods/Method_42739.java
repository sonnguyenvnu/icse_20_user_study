/** 
 * ????
 * @param rpMicroSubmitRecord
 * @param pageParam
 * @param model
 * @return
 */
@RequiresPermissions("trade:micro:submit:record:list") @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET}) public void list(HttpServletRequest request,RpMicroSubmitRecord rpMicroSubmitRecord,PageParam pageParam,Model model){
  PageBean pageBean=rpMicroSubmitRecordService.listPage(pageParam,rpMicroSubmitRecord);
  model.addAttribute("pageBean",pageBean);
  model.addAttribute("pageParam",pageParam);
  model.addAttribute("rpMicroSubmitRecord",rpMicroSubmitRecord);
}
