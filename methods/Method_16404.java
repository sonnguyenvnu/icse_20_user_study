/** 
 * ????ID?????????,?????? ?: http://host:port/file/download/aSk2a/file.zip ??? ID?aSk2a???.????file.zip
 * @param idOrMd5  ????????id??md5?
 * @param name     ???????????????????.???????(null).????????????
 * @param response {@link javax.servlet.http.HttpServletResponse}
 * @param request  {@link javax.servlet.http.HttpServletRequest}
 * @return ????, ??????, ???????
 * @throws IOException                              ??????
 * @throws org.hswebframework.web.NotFoundException ?????
 */
@GetMapping(value="/download/{id}") @ApiOperation("????") @Authorize(action="download",description="????") public void downLoad(@ApiParam("???id??md5") @PathVariable("id") String idOrMd5,@ApiParam(value="???,?????,??????????",required=false) @RequestParam(value="name",required=false) String name,@ApiParam(hidden=true) HttpServletResponse response,@ApiParam(hidden=true) HttpServletRequest request) throws IOException {
  FileInfoEntity fileInfo=fileInfoService.selectByIdOrMd5(idOrMd5);
  if (fileInfo == null || !DataStatus.STATUS_ENABLED.equals(fileInfo.getStatus())) {
    throw new NotFoundException("?????");
  }
  String fileName=fileInfo.getName();
  String suffix=fileName.contains(".") ? fileName.substring(fileName.lastIndexOf("."),fileName.length()) : "";
  String contentType=fileInfo.getType() == null ? MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileName) : fileInfo.getType();
  if (StringUtils.isNullOrEmpty(name)) {
    name=fileInfo.getName();
  }
  if (!name.contains(".")) {
    name=name.concat(".").concat(suffix);
  }
  name=fileNameKeyWordPattern.matcher(name).replaceAll("");
  int skip=0;
  long fSize=fileInfo.getSize();
  try {
    String Range=request.getHeader("Range").replace("bytes=","").replace("-","");
    skip=StringUtils.toInt(Range);
  }
 catch (  Exception ignore) {
  }
  response.setContentLength((int)fSize);
  response.setContentType(contentType);
  response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(name,"utf-8"));
  if (skip > 0) {
    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
    String contentRange="bytes " + skip + "-" + (fSize - 1) + "/" + fSize;
    response.setHeader("Content-Range",contentRange);
  }
  fileService.writeFile(idOrMd5,response.getOutputStream(),skip);
}
