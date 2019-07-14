/** 
 * ????UI
 * @return
 */
@SuppressWarnings("unchecked") @RequestMapping("/assignMenuUI") public String assignMenuUI(HttpServletRequest req,Model model,Long roleId){
  PmsRole role=pmsRoleService.getDataById(roleId);
  if (role == null) {
    return operateError("????????",model);
  }
  if (OperatorTypeEnum.USER.name().equals(this.getPmsOperator().getType()) && "admin".equals(role.getRoleName())) {
    return operateError("????",model);
  }
  String menuIds=pmsMenuService.getMenuIdsByRoleId(roleId);
  List menuList=pmsMenuService.getListByParent(null);
  List<PmsOperator> operatorList=pmsOperatorRoleService.listOperatorByRoleId(roleId);
  model.addAttribute("menuIds",menuIds);
  model.addAttribute("menuList",menuList);
  model.addAttribute("operatorList",operatorList);
  model.addAttribute("role",role);
  return "/pms/assignMenuUI";
}
