/** 
 * ???????????????????
 * @return
 */
@GetMapping("/menu") public ResultData menu(){
  List<SubMenuVO> list=menuService.getMenuByUserRole(this.getUserNameByToken());
  return ResultData.builder().data(list).build();
}
