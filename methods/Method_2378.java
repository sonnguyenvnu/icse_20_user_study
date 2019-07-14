@RequestMapping(value="/signup",method=RequestMethod.POST) @ResponseBody public Object signup(HttpServletRequest request){
  return new UcenterResult(UcenterResultConstant.SUCCESS,"");
}
