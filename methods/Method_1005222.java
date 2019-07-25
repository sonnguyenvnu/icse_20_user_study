/** 
 * ??sql????????
 * @return
 */
@RequestMapping(params="addorupdate") public ModelAndView addorupdate(CgformButtonSqlEntity cgformButtonSql,HttpServletRequest req){
  cgformButtonSql.setButtonCode("add");
  if (StringUtil.isNotEmpty(cgformButtonSql.getButtonCode()) && StringUtil.isNotEmpty(cgformButtonSql.getFormId())) {
    CgformButtonSqlEntity cgformButtonSqlVo=cgformButtonSqlService.getCgformButtonSqlByCodeFormId(cgformButtonSql.getButtonCode(),cgformButtonSql.getFormId());
    if (cgformButtonSqlVo != null) {
      cgformButtonSql=cgformButtonSqlVo;
    }
  }
  List<CgformButtonEntity> list=cgformButtonService.getCgformButtonListByFormId(cgformButtonSql.getFormId());
  if (list == null) {
    list=new ArrayList<CgformButtonEntity>();
  }
  req.setAttribute("buttonList",list);
  req.setAttribute("cgformButtonSqlPage",cgformButtonSql);
  return new ModelAndView("jeecg/cgform/button/cgformButtonSql");
}
