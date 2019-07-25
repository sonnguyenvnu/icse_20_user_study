/** 
 * ??????-??
 * @param generateEntity
 * @param request
 * @return
 * @throws Exception 
 */
@RequestMapping(params="dogenerate") public void dogenerate(CgFormHeadEntity cgFormHead,GenerateEntity generateEntity,CreateFileProperty createFileProperty,HttpServletRequest request,HttpServletResponse response) throws Exception {
  if (StringUtil.isNotEmpty(cgFormHead.getId())) {
    cgFormHead=cgFormFieldService.getEntity(CgFormHeadEntity.class,cgFormHead.getId());
    getCgformConfig(cgFormHead,generateEntity);
  }
 else {
    throw new RuntimeException("???????");
  }
  AjaxJson j=new AjaxJson();
  String tableName=generateEntity.getTableName();
  String ftlDescription=generateEntity.getFtlDescription();
  try {
    boolean tableexist=new JeecgReadTable().checkTableExist(tableName);
    if (tableexist) {
      String version=request.getParameter("version");
      OnlineGenerateEnum modeEnum=OnlineGenerateEnum.toEnum(createFileProperty.getJspMode(),version);
      if (modeEnum != null) {
        if ("ext".equals(modeEnum.getVersion())) {
          CgformCodeGenerate generate=new CgformCodeGenerate(createFileProperty,generateEntity);
          generate.generateToFileUserDefined();
        }
 else         if ("ext-common".equals(modeEnum.getVersion())) {
          CreateFileConfig createFileConfig=new CreateFileConfig();
          createFileConfig.setStylePath(createFileProperty.getJspMode().replace(".",File.separator));
          createFileConfig.setTemplateRootDir("src/main/resources/jeecg/ext-common-template");
          CgformCodeExtCommonGenerate g=new CgformCodeExtCommonGenerate(createFileConfig,generateEntity);
          g.generateToFile();
        }
        j.setMsg(ftlDescription + "???????????????????????" + CodeStringUtils.getInitialSmall(generateEntity.getEntityName()) + "Controller.do?list");
      }
 else       if ("system".equals(version)) {
        CgformCodeGenerate generate=new CgformCodeGenerate(createFileProperty,generateEntity);
        createFileProperty.setJspMode(OnlineGenerateEnum.ONLINE_TABLE_SINGLE.getCode());
        Map<String,Object> cgformFtlEntity=cgformFtlService.getCgformFtlByTableName(tableName);
        if (cgformFtlEntity != null) {
          String formhtml=templetContextWord.autoFormGenerateHtml(tableName,null,null);
          generate.setCgformJspHtml(formhtml);
        }
 else {
          j.setMsg("????????word??????");
          try {
            String projectPath=URLEncoder.encode(generateEntity.getProjectPath(),"UTF-8");
            Cookie cookie=new Cookie("cookie_projectPath",projectPath);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.getWriter().print(j.getJsonStr());
            response.getWriter().flush();
          }
 catch (          IOException e) {
            e.printStackTrace();
          }
 finally {
            try {
              response.getWriter().close();
            }
 catch (            Exception e2) {
            }
          }
          return;
        }
        generate.generateToFileUserDefined();
      }
 else {
        j.setMsg("?????????????");
      }
    }
 else {
      j.setMsg("?[" + tableName + "] ?????????");
    }
  }
 catch (  Exception e1) {
    e1.printStackTrace();
    j.setMsg(e1.getMessage());
    throw new RuntimeException(e1.getMessage());
  }
  try {
    String projectPath=URLEncoder.encode(generateEntity.getProjectPath(),"UTF-8");
    Cookie cookie=new Cookie("cookie_projectPath",projectPath);
    cookie.setMaxAge(60 * 60 * 24 * 30);
    response.addCookie(cookie);
    response.getWriter().print(j.getJsonStr());
    response.getWriter().flush();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      response.getWriter().close();
    }
 catch (    Exception e2) {
    }
  }
}
