@RequestMapping("/detail.do") @ResponseBody @AuthPassport(authority=C_AUTH_USER) public JsonResult detail(String id){
  User user=new User();
  if (id != null) {
    user=userService.getById(id);
  }
  return new JsonResult().data(UserAdapter.getDto(user));
}
