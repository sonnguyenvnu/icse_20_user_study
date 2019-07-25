@GetMapping("/find/{username}") public Result<User> find(@PathVariable("username") String username){
  User user=userService.findByUsername(username);
  return ResultHelper.newSuccessResult(user);
}
