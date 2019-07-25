/** 
 * ??XML??
 * @return
 */
@RequestMapping(params="createxml") public void createxml(HttpServletRequest request,HttpServletResponse response){
  String field=request.getParameter("field");
  String entityname=request.getParameter("entityname");
  ImportFile importFile=new ImportFile(request,response);
  importFile.setField(field);
  importFile.setEntityName(entityname);
  importFile.setFileName(entityname + ".bak");
  importFile.setEntityClass(MyClassLoader.getClassByScn(entityname));
  systemService.createXml(importFile);
}
