/** 
 * ????????
 * @param sourceId ???ID
 * @param targetId ????ID
 * @param startSlot ??slot
 * @param endSlot ??slot
 * @param appId ??id
 * @param appAuditId ??id
 * @return
 */
@RequestMapping(value="/checkHorizontalScale") public ModelAndView doCheckHorizontalScale(HttpServletRequest request,HttpServletResponse response,Model model,long sourceId,long targetId,int startSlot,int endSlot,long appId,long appAuditId,int migrateType){
  HorizontalResult horizontalResult=appDeployCenter.checkHorizontal(appId,appAuditId,sourceId,targetId,startSlot,endSlot,migrateType);
  model.addAttribute("status",horizontalResult.getStatus());
  model.addAttribute("message",horizontalResult.getMessage());
  return new ModelAndView("");
}
