/** 
 * ???????????
 */
@RequestMapping("download") public void download(HttpServletRequest request,HttpServletResponse response,String templateId){
  String fileName="student.xls";
  response.setContentType("application/octet-stream");
  response.setHeader("Content-Disposition","attachment;filename=" + fileName);
  String xmlPath=getClass().getClassLoader().getResource("config/excel/student.xml").getPath();
  String filePath=getClass().getClassLoader().getResource("config/excel/").getPath();
  File xmlFile=new File(xmlPath);
  File excelFile=new File(filePath + fileName);
  try {
    CreateTemplateUtils.create(xmlFile,excelFile);
    logger.info("?????{}",excelFile.getName());
    InputStream in=FileUtils.openInputStream(excelFile);
    int b;
    while ((b=in.read()) != -1) {
      response.getOutputStream().write(b);
    }
  }
 catch (  Exception e) {
    logger.info("?????????{}",e);
    e.printStackTrace();
  }
}
