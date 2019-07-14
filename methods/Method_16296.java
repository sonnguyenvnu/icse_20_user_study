@PostMapping("/write") @ApiOperation("????") @Authorize(action="write",description="????") public ResponseMessage<String> write(@RequestBody List<GeneratedCode> codes){
  return ResponseMessage.ok(codeWriter.write(codes));
}
