/** 
 * ??
 * @return ??
 */
@RequestMapping(value="/",method=RequestMethod.GET) @ApiIgnore() @ApiOperation(value="????api??") public ModelAndView api(){
  return new ModelAndView("redirect:/swagger-ui.html");
}
