/** 
 * ????
 * @param columnInfos
 * @return
 */
@PostMapping(value="/generator") public ResponseEntity generator(@RequestBody List<ColumnInfo> columnInfos,@RequestParam String tableName){
  if (!generatorEnabled) {
    throw new BadRequestException("???????????");
  }
  generatorService.generator(columnInfos,genConfigService.find(),tableName);
  return new ResponseEntity(HttpStatus.OK);
}
