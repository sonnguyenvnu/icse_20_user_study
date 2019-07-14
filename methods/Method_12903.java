private Object buildUserModel(AnalysisContext context,List<String> stringList) throws Exception {
  ExcelHeadProperty excelHeadProperty=context.getExcelHeadProperty();
  Object resultModel=excelHeadProperty.getHeadClazz().newInstance();
  if (excelHeadProperty == null) {
    return resultModel;
  }
  BeanMap.create(resultModel).putAll(TypeUtil.getFieldValues(stringList,excelHeadProperty,context.use1904WindowDate()));
  return resultModel;
}
