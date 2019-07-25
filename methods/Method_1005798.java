/** 
 * ????
 * @param id
 * @param parentId
 * @param type ??????id?null?parentId?null?????????????????type????????
 * @return
 */
@RequestMapping("/menu/detail.do") @ResponseBody @AuthPassport(authority=C_AUTH_MENU) public JsonResult detail(String id,String parentId,String type){
  Menu menu=new Menu();
  menu.setParentId(parentId);
  Menu parentMenu=menuService.getById(parentId);
  if (id != null) {
    menu=menuService.getById(id);
  }
 else {
    menu.setType(parentMenu == null ? type : parentMenu.getType());
  }
  MenuDto menuDto=MenuAdapter.getDto(menu);
  return new JsonResult().data(menuDto);
}
