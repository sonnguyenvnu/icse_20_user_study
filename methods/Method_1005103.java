@RequestMapping(params="testsubmit=1",method={RequestMethod.GET,RequestMethod.POST}) public ModelAndView testsubmit(HttpServletRequest request){
  logger.info("????byebye");
  return new ModelAndView("com/jeecg/demo/form_valid");
}
