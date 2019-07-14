/** 
 * ??????
 * @return listPmsRole or operateError .
 */
@RequiresPermissions("pms:role:view") @RequestMapping("/list") public String listPmsRole(HttpServletRequest req,PageParam pageParam,PmsRole pmsRole,Model model){
  try {
    PageBean pageBean=pmsRoleService.listPage(pageParam,pmsRole);
    model.addAttribute(pageBean);
    model.addAttribute("pageParam",pageParam);
    model.addAttribute("pmsRole",pmsRole);
    return "pms/pmsRoleList";
  }
 catch (  Exception e) {
    log.error("== listPmsRole exception:",e);
    return operateError("??????",model);
  }
}
