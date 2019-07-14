@RequestMapping(value="/signin",method=RequestMethod.POST) @ResponseBody public Object signin(HttpServletRequest request){
  return new UcenterResult(UcenterResultConstant.SUCCESS,"");
}
