/** 
 * ???????? .
 * @return editPmsRoleUI or operateError .
 */
@RequiresPermissions("pms:role:edit") @RequestMapping("/editUI") public String editPmsRoleUI(HttpServletRequest req,Model model,Long roleId){
  try {
    PmsRole pmsRole=pmsRoleService.getDataById(roleId);
    if (pmsRole == null) {
      return operateError("??????",model);
    }
    model.addAttribute(pmsRole);
    return "/pms/pmsRoleEdit";
  }
 catch (  Exception e) {
    log.error("== editPmsRoleUI exception:",e);
    return operateError("??????",model);
  }
}
