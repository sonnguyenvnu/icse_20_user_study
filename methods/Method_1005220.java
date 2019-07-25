/** 
 * ?????????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(CgformButtonEntity cgformButton,HttpServletRequest req){
  if (StringUtil.isNotEmpty(cgformButton.getId())) {
    cgformButton=cgformButtonService.getEntity(CgformButtonEntity.class,cgformButton.getId());
  }
  req.setAttribute("cgformButtonPage",cgformButton);
  return new ModelAndView("jeecg/cgform/button/cgformButton");
}
