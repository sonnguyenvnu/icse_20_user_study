/** 
 * ??????????html??????pdf??
 * @param id
 * @param moduleId
 * @param secretKey
 * @return
 * @throws Exception
 */
@RequestMapping("/detail/pdf.do") public String pdf(String id,String moduleId,@RequestParam String secretKey) throws Exception {
  HttpServletRequest request=ThreadContext.request();
  try {
    if (MyString.isEmpty(id) && MyString.isEmpty(moduleId)) {
      request.setAttribute("result","??ID&??ID???????");
      return ERROR_VIEW;
    }
    if (!secretKey.equals(settingCache.get(S_SECRETKEY).getValue())) {
      request.setAttribute("result","???????????");
      return ERROR_VIEW;
    }
    if (MyString.isEmpty(id) && MyString.isEmpty(moduleId)) {
      request.setAttribute("result","?????????PDF???");
      return ERROR_VIEW;
    }
    List<InterfacePDFDto> interfacePDFDtos=new ArrayList<>();
    request.setAttribute("MAIN_COLOR",settingCache.get("MAIN_COLOR").getValue());
    request.setAttribute("ADORN_COLOR",settingCache.get("ADORN_COLOR").getValue());
    request.setAttribute("title",settingCache.get("TITLE").getValue());
    if (!MyString.isEmpty(id)) {
      InterfaceWithBLOBs interFace=interfaceService.getById(id);
      if (interFace == null) {
        request.setAttribute("result",ERROR_INTERFACE_ID);
        return ERROR_VIEW;
      }
      Module module=moduleCache.get(interFace.getModuleId());
      InterfacePDFDto interDto=interfaceService.getInterPDFDto(interFace,module,true,true);
      interfacePDFDtos.add(interDto);
      request.setAttribute("interfaces",interfacePDFDtos);
      request.setAttribute("moduleName",module.getName());
      return "/WEB-INF/views/interFacePdf.jsp";
    }
    Module module=moduleService.getById(moduleId);
    if (module == null) {
      request.setAttribute("result",ERROR_MODULE_ID);
      return ERROR_VIEW;
    }
    for (    InterfaceWithBLOBs interFace : interfaceService.queryAll(new InterfaceQuery().setModuleId(moduleId))) {
      InterfacePDFDto interDto=interfaceService.getInterPDFDto(interFace,module,true,true);
      interfacePDFDtos.add(interDto);
    }
    request.setAttribute("interfaces",interfacePDFDtos);
    request.setAttribute("moduleName",module.getName());
    return "/WEB-INF/views/interFacePdf.jsp";
  }
 catch (  Exception e) {
    log.error("????????,id:" + id + ",moduleId:" + moduleId,e);
    request.setAttribute("result","?????????????????????" + e.getMessage());
    return ERROR_VIEW;
  }
}
