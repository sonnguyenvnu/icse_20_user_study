/** 
 * ??????
 */
@RequestMapping("/list/{pageNumber}") public String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
  Page<SysMenu> page=getPage(pageNumber,pageSize);
  page.setAsc("code");
  QueryWrapper<SysMenu> ew=new QueryWrapper<SysMenu>();
  if (StringUtils.isNotBlank(search)) {
    ew.like("menu_name",search);
    model.addAttribute("search",search);
  }
  Page<SysMenu> pageData=(Page<SysMenu>)sysMenuService.page(page,ew);
  for (  SysMenu menu : pageData.getRecords()) {
    if (menu.getParentId() == null || menu.getDeep() != 3) {
      menu.setMenuName(StringUtils.join("<i class='fa fa-folder-open'></i> ",menu.getMenuName()));
    }
 else {
      menu.setMenuName(StringUtils.join("<i class='fa fa-file'></i> ",menu.getMenuName()));
    }
    for (int i=1; i < menu.getDeep(); i++) {
      menu.setMenuName(StringUtils.join("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",menu.getMenuName()));
    }
  }
  model.addAttribute("pageData",pageData);
  return "ftl/admin/menu/list";
}
