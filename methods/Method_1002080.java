/** 
 * ????
 * @author stylefeng
 * @Date 2019-03-13
 */
@RequestMapping("/edit") public String edit(@RequestParam("dictId") Long dictId,Model model){
  Dict dict=dictService.getById(dictId);
  if (dict == null) {
    throw new RequestEmptyException();
  }
  DictType dictType=dictTypeService.getById(dict.getDictTypeId());
  if (dictType == null) {
    throw new RequestEmptyException();
  }
  model.addAttribute("dictTypeId",dict.getDictTypeId());
  model.addAttribute("dictTypeName",dictType.getName());
  return PREFIX + "/dict_edit.html";
}
