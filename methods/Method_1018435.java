@RequestMapping(value="stock",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) public Object stock(long id){
  int result=personService.updateAge(id);
  return result > 0;
}
