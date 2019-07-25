/** 
 * ?????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(MutiLangEntity mutiLang,HttpServletRequest req){
  if (StringUtil.isNotEmpty(mutiLang.getId())) {
    mutiLang=systemService.getEntity(MutiLangEntity.class,mutiLang.getId());
    req.setAttribute("mutiLangPage",mutiLang);
    mutiLangService.putMutiLang(mutiLang);
  }
  return new ModelAndView("system/mutilang/mutiLang");
}
