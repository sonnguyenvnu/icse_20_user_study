@GetMapping("/tree") @RequiresPermissions({}) @ApiOperation(value="???????",notes="?????????????????????????????") public CommonResult<List<DataDictEnumVO>> tree(){
  List<DataDictBO> dataDicts=dataDictService.selectDataDictList();
  ImmutableListMultimap<String,DataDictBO> dataDictMap=Multimaps.index(dataDicts,DataDictBO::getEnumValue);
  List<DataDictEnumVO> dataDictEnumVOs=new ArrayList<>(dataDictMap.size());
  dataDictMap.keys().forEach(enumValue -> {
    DataDictEnumVO dataDictEnumVO=new DataDictEnumVO().setEnumValue(enumValue).setValues(DataDictConvert.INSTANCE.convert2(dataDictMap.get(enumValue)));
    dataDictEnumVOs.add(dataDictEnumVO);
  }
);
  return success(dataDictEnumVOs);
}
