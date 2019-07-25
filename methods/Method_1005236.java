/** 
 * easyui AJAX????
 * @param request
 * @param response
 * @param dataGrid
 * @param
 */
@RequestMapping(params="datagrid") public void datagrid(CgformTemplateEntity cgformTemplate,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
  CriteriaQuery cq=new CriteriaQuery(CgformTemplateEntity.class,dataGrid);
  org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,cgformTemplate,request.getParameterMap());
  try {
  }
 catch (  Exception e) {
    throw new BusinessException(e.getMessage());
  }
  cq.add();
  this.cgformTemplateService.getDataGridReturn(cq,true);
  List<CgformTemplateEntity> dataList=dataGrid.getResults();
  if (dataList != null && dataList.size() > 0) {
    for (    CgformTemplateEntity entity : dataList) {
      if (oConvertUtils.isNotEmpty(entity.getTemplatePic())) {
        entity.setTemplatePic("img-online/server/" + entity.getTemplateCode() + "/images/" + entity.getTemplatePic());
      }
 else {
        entity.setTemplatePic("img-online/server/default/images/default.jpg");
      }
    }
  }
  TagUtil.datagrid(response,dataGrid);
}
