/** 
 * ????
 * @return
 * @throws IOException
 */
@ResponseBody @RequestMapping("query") public List<Rule> query() throws IOException {
  return ruleService.findAll();
}
