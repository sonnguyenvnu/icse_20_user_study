@GetMapping("/list") public CommonResult<UsersCartDetailVO> list(){
  return getCartDetail();
}
