@RequestMapping(params="log") public ModelAndView log(){
  return new ModelAndView("com/jeecg/demo/logList");
}
