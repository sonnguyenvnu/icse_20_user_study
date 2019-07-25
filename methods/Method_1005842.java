/** 
 * recover by log ??????
 * @param log
 * @throws MyException
 */
public void recover(Log log) throws MyException {
  log=getById(log.getId());
switch (log.getModelClass().toUpperCase()) {
case "INTERFACEWITHBLOBS":
case "INTERFACE":
    JSONObject json=JSONObject.fromObject(log.getContent());
  InterfaceWithBLOBs inter=(InterfaceWithBLOBs)JSONObject.toBean(json,InterfaceWithBLOBs.class);
checkModule(inter.getModuleId());
checkLog(inter.getProjectId());
interfaceService.update(inter);
break;
case "ARTICLEWITHBLOBS":
case "ARTICLE":
json=JSONObject.fromObject(log.getContent());
ArticleWithBLOBs article=(ArticleWithBLOBs)JSONObject.toBean(json,ArticleWithBLOBs.class);
checkModule(article.getModuleId());
checkLog(article.getProjectId());
if (MyString.isEmpty(article.getMkey())) {
article.setMkey(null);
}
articleService.update(article);
break;
case "MODULEWITHBLOBS":
case "MODULE":
json=JSONObject.fromObject(log.getContent());
Module module=(Module)JSONObject.toBean(json,Module.class);
checkLog(module.getProjectId());
moduleService.update(module);
break;
case "PROJECTWITHBLOBS":
case "PROJECT":
json=JSONObject.fromObject(log.getContent());
Project project=(Project)JSONObject.toBean(json,Log.class);
projectService.update(project);
break;
case "SOURCEWITHBLOBS":
case "SOURCE":
json=JSONObject.fromObject(log.getContent());
Source source=(Source)JSONObject.toBean(json,Source.class);
checkModule(source.getModuleId());
checkLog(source.getProjectId());
sourceService.update(source);
break;
}
}
