/** 
 * ??????
 * @param sourceId ???ID
 * @param targetId ????ID
 * @param startSlot ??slot
 * @param endSlot ??slot
 * @param appId ??id
 * @param appAuditId ??id
 * @return
 */
@RequestMapping(value="/startHorizontalScale") public ModelAndView doStartHorizontalScale(HttpServletRequest request,HttpServletResponse response,Model model,long sourceId,long targetId,int startSlot,int endSlot,long appId,long appAuditId,int migrateType){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} horizontalScaleApply appId {} appAuditId {} sourceId {} targetId {} startSlot {} endSlot {}",appUser.getName(),appId,appAuditId,sourceId,targetId,startSlot,endSlot);
  HorizontalResult horizontalResult=appDeployCenter.startHorizontal(appId,appAuditId,sourceId,targetId,startSlot,endSlot,migrateType);
  model.addAttribute("status",horizontalResult.getStatus());
  model.addAttribute("message",horizontalResult.getMessage());
  return new ModelAndView("");
}
