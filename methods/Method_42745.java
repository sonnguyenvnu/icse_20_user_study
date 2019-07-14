/** 
 * ????????.
 * @return PmsMenuList .
 */
@RequiresPermissions("pms:menu:view") @RequestMapping("/list") public String listPmsMenu(HttpServletRequest req,Model model){
  String editMenuController="pms/menu/editUI";
  String str=pmsMenuBiz.getTreeMenu(editMenuController);
  model.addAttribute("tree",str);
  return "pms/pmsMenuList";
}
