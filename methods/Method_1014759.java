@GetMapping("/query") public ReturnItems<User> query(@RequestParam(value="offset",defaultValue="0") Integer offset,@RequestParam(value="limit",defaultValue="30") Integer limit){
  Page<User> page=userService.query(offset,limit);
  return ReturnItemUtils.newSuccessReturnItems(page.getResult(),page.getTotal());
}
