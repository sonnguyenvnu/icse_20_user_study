@GetMapping("/list") @ApiOperation("??????????,??????") @Authorize(action="read",description="????") public ResponseMessage<List<FileInfo>> write(@RequestParam String path){
  File file=new File(path);
  if (!file.exists()) {
    return ResponseMessage.error(404,"?????");
  }
  List<FileInfo> list;
  if (file.isDirectory()) {
    File[] files=file.listFiles();
    if (files == null) {
      list=new java.util.ArrayList<>();
    }
 else {
      list=Stream.of(files).map(FileInfo::from).collect(Collectors.toList());
    }
  }
 else {
    list=Collections.singletonList(FileInfo.from(file));
  }
  return ResponseMessage.ok(list);
}
