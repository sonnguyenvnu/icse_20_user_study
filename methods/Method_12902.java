@Override public void invoke(Object object,AnalysisContext context){
  if (context.getExcelHeadProperty() != null && context.getExcelHeadProperty().getHeadClazz() != null) {
    try {
      Object resultModel=buildUserModel(context,(List<String>)object);
      context.setCurrentRowAnalysisResult(resultModel);
    }
 catch (    Exception e) {
      throw new ExcelGenerateException(e);
    }
  }
}
