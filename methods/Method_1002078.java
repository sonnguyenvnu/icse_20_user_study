/** 
 * ??????
 * @author stylefeng
 * @Date 2019-03-13
 */
@RequestMapping("") public String index(@RequestParam("dictTypeId") Long dictTypeId,Model model){
  model.addAttribute("dictTypeId",dictTypeId);
  DictType dictType=dictTypeService.getById(dictTypeId);
  if (dictType == null) {
    throw new RequestEmptyException();
  }
  model.addAttribute("dictTypeName",dictType.getName());
  return PREFIX + "/dict.html";
}
