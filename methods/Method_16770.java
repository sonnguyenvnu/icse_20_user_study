@GetMapping("/{processInstanceId}/image") @ApiOperation("?????????????") @Authorize(action=Permission.ACTION_QUERY) public void getProcessImage(@PathVariable String processInstanceId,HttpServletResponse response) throws IOException {
  try (InputStream inputStream=bpmProcessService.findProcessPic(processInstanceId)){
    response.setContentType(MediaType.IMAGE_PNG_VALUE);
    ImageIO.write(ImageIO.read(inputStream),"png",response.getOutputStream());
  }
 }
