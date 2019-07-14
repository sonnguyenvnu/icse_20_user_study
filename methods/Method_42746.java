/** 
 * ????????.
 * @return
 */
@RequiresPermissions("pms:menu:edit") @RequestMapping("/editUI") public String editPmsMenuUI(HttpServletRequest req,Long id,Model model){
  if (null != id) {
    PmsMenu pmsMenu=pmsMenuService.getById(id);
    model.addAttribute(pmsMenu);
  }
  return "pms/pmsMenuEdit";
}
