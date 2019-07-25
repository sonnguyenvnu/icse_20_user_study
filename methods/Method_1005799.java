@RequestMapping("/menu/delete.do") @ResponseBody @AuthPassport(authority=C_AUTH_MENU) public JsonResult delete(@RequestParam String id) throws MyException {
  if (menuService.count(new MenuQuery().setParentId(id)) > 0) {
    throw new MyException(MyError.E000025);
  }
  menuService.delete(id);
  objectCache.del(C_CACHE_MENU);
  return SUCCESS;
}
