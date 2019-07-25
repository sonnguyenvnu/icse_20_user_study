/** 
 * WebUploader ??????/????
 */
@RequestMapping("/filedeal") @ResponseBody public AjaxJson filedeal(HttpServletRequest request,HttpServletResponse response){
  AjaxJson j=new AjaxJson();
  String msg="????-??????";
  String upFlag=request.getParameter("isup");
  String delFlag=request.getParameter("isdel");
  String swfTransform=request.getParameter("swfTransform");
  String ctxPath=ResourceUtil.getConfigByName("webUploadpath");
  String globalSwfTransformFlag=ResourceUtil.getConfigByName("swf.transform.flag");
  logger.debug("----ctxPath-----" + ctxPath);
  try {
    if ("1".equals(upFlag)) {
      String fileName=null;
      String bizType=request.getParameter("bizType");
      logger.debug("---bizType----" + bizType);
      String bizPath=StoreUploadFilePathEnum.getPath(bizType);
      String nowday=new SimpleDateFormat("yyyyMMdd").format(new Date());
      logger.debug("---nowday----" + nowday);
      File file=new File(ctxPath + File.separator + bizPath + File.separator + nowday);
      if (!file.exists()) {
        file.mkdirs();
      }
      MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
      MultipartFile mf=multipartRequest.getFile("file");
      String orgName=mf.getOriginalFilename();
      fileName=orgName.substring(0,orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
      String savePath=file.getPath() + File.separator + fileName;
      String fileExt=FileUtils.getExtend(fileName);
      if ("txt".equals(fileExt)) {
        FileUtils.uploadTxtFile(mf,savePath);
      }
 else {
        File savefile=new File(savePath);
        FileCopyUtils.copy(mf.getBytes(),savefile);
      }
      msg="????";
      j.setMsg(msg);
      String dbpath=bizPath + File.separator + nowday + File.separator + fileName;
      logger.debug("---dbpath----" + dbpath);
      if (dbpath.contains("\\")) {
        dbpath=dbpath.replace("\\","/");
      }
      j.setObj(dbpath);
      if ("true".equals(globalSwfTransformFlag) && "true".equals(swfTransform)) {
        SwfToolsUtil.convert2SWF(savePath);
      }
    }
 else     if ("1".equals(delFlag)) {
      String path=request.getParameter("path");
      String delpath=ctxPath + File.separator + path;
      File fileDelete=new File(delpath);
      if (!fileDelete.exists() || !fileDelete.isFile()) {
        msg="??: " + delpath + "???!";
        logger.info(msg);
        j.setSuccess(true);
      }
 else {
        if (fileDelete.delete()) {
          msg="--------??????---------" + delpath;
          logger.info(msg);
          if ("true".equals(globalSwfTransformFlag) && "true".equals(swfTransform)) {
            try {
              String swfPath=FileUtils.getSwfPath(delpath);
              new File(swfPath).delete();
              logger.info("--------????swf??---------" + swfPath);
              if (!delpath.endsWith("pdf")) {
                String pdfPath=delpath.substring(0,delpath.lastIndexOf(".") + 1) + "pdf";
                new File(pdfPath).delete();
                logger.info("--------????pdf??---------" + pdfPath);
              }
            }
 catch (            Exception e) {
              logger.info("swf??ORpdf???????");
            }
          }
        }
 else {
          j.setSuccess(false);
          msg="?????--jdk????????????????";
          logger.info(msg);
        }
      }
    }
 else {
      throw new BusinessException("???????????????");
    }
  }
 catch (  IOException e) {
    j.setSuccess(false);
    logger.info(e.getMessage());
  }
catch (  BusinessException b) {
    j.setSuccess(false);
    logger.info(b.getMessage());
  }
  logger.debug("-----systemController/filedeal.do------------" + msg);
  j.setMsg(msg);
  return j;
}
