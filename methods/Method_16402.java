/** 
 * ?????zip??.???POST??
 * @param name     ???
 * @param dataStr  ??,jsonArray. ??:[{"name":"fileName","text":"fileText"}]
 * @param response {@link HttpServletResponse}
 * @throws IOException      ??zip????
 * @throws RuntimeException ??zip????
 */
@RequestMapping(value="/download-zip/{name:.+}",method={RequestMethod.POST}) @ApiOperation("??zip?????") @Authorize(action="download",description="????") public void downloadZip(@ApiParam("zip???") @PathVariable("name") String name,@ApiParam(value="zip????",example="[" + "{\"name\":\"textFile.txt\",\"text\":\"fileText\"}," + "{\"name\":\"uploadedFile.png\",\"file\":\"fileId or file md5\"}" + "{\"name\":\"base64File.text\",\"base64\":\"aGVsbG8=\"}" + "]") @RequestParam("data") String dataStr,HttpServletResponse response) throws IOException {
  response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
  response.setHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(name,"utf-8"));
  ZIPWriter writer=Compress.zip();
  List<Map<String,String>> data=(List)JSON.parseArray(dataStr,Map.class);
  data.forEach(map -> {
    String entryName=map.get("name");
    String text=map.get("text");
    String file=map.get("file");
    String fileBase64=map.get("base64");
    if (text != null) {
      writer.addTextFile(map.get("name"),text);
    }
 else     if (file != null) {
      writer.addFile(entryName,fileService.readFile(file));
    }
 else     if (fileBase64 != null) {
      writer.addFile(entryName,new ByteArrayInputStream(Base64.decodeBase64(fileBase64)));
    }
  }
);
  writer.write(response.getOutputStream());
}
