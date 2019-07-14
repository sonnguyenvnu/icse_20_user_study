@GetMapping(value="/md5/{md5}") @ApiOperation("??MD5??????") public ResponseMessage<FileInfoEntity> uploadStatic(@PathVariable String md5) throws IOException {
  return ofNullable(fileInfoService.selectByMd5(md5)).map(ResponseMessage::ok).orElseThrow(() -> new NotFoundException("file not found"));
}
