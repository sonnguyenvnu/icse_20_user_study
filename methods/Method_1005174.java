@ApiOperation(value="??TOKEN") @RequestMapping(method=RequestMethod.POST) @ResponseBody public ResponseEntity<?> login(@RequestParam String username,@RequestParam String password){
  logger.info("??TOKEN[{}]",username);
  if (StringUtils.isEmpty(username)) {
    return new ResponseEntity("????????!",HttpStatus.NOT_FOUND);
  }
  if (StringUtils.isEmpty(username)) {
    return new ResponseEntity("????????!",HttpStatus.NOT_FOUND);
  }
  Assert.notNull(username,"username can not be empty");
  Assert.notNull(password,"password can not be empty");
  TSUser user=userService.checkUserExits(username,password);
  if (user == null) {
    logger.info("??TOKEN,???????[{}]",username);
    return new ResponseEntity("????????!",HttpStatus.NOT_FOUND);
  }
  String token=tokenManager.createToken(user);
  return new ResponseEntity(token,HttpStatus.OK);
}
