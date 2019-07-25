@RequestMapping(value={"/*","/"},method=RequestMethod.GET) public Object index() throws Exception {
  logger.info("this is Redis Sample!");
  return "this is Redis Sample!";
}
