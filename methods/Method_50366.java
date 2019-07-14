@PostMapping(value="/listAppName") @Permission public AjaxResponse listAppName(){
  final List<String> list=applicationNameService.list();
  return AjaxResponse.success(list);
}
