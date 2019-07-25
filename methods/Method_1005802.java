@RequestMapping("/list.do") @ResponseBody @AuthPassport(authority=C_AUTH_USER) public JsonResult list(@ModelAttribute UserQuery query) throws MyException {
  Page page=new Page(query);
  page.setAllRow(userService.count(query));
  return new JsonResult(1,UserAdapter.getDto(userService.query(query)),page);
}
