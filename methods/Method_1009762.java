@RequestMapping(method=RequestMethod.POST,value="/bars") @ResponseStatus(HttpStatus.CREATED) @ResponseBody public Bar create(@RequestBody final Bar bar){
  bar.setId(Long.parseLong(randomNumeric(2)));
  return bar;
}
