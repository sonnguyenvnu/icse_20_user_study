/** 
 * ??java????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(CgformEnhanceJavaEntity cgformEnhanceJavaEntity,HttpServletRequest req){
  cgformEnhanceJavaEntity.setButtonCode("add");
  if (StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getButtonCode()) && StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getFormId())) {
    CgformEnhanceJavaEntity cgformEnhanceJavaEntityVo=cgformEnhanceJavaService.getCgformEnhanceJavaEntityByCodeFormId(cgformEnhanceJavaEntity.getButtonCode(),cgformEnhanceJavaEntity.getFormId(),cgformEnhanceJavaEntity.getEvent());
    if (cgformEnhanceJavaEntityVo != null) {
      cgformEnhanceJavaEntity=cgformEnhanceJavaEntityVo;
    }
  }
  List<CgformButtonEntity> list=cgformButtonService.getCgformButtonListByFormId(cgformEnhanceJavaEntity.getFormId());
  if (list == null) {
    list=new ArrayList<CgformButtonEntity>();
  }
  req.setAttribute("buttonList",list);
  req.setAttribute("cgformEnhanceJavaPage",cgformEnhanceJavaEntity);
  return new ModelAndView("jeecg/cgform/enhance/cgformEnhanceJava");
}
