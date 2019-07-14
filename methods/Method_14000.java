@Override public String fromCell(Cell cell,ExpressionContext ctxt) throws SkipSchemaExpressionException {
  if (cell.value != null && !cell.value.toString().isEmpty()) {
    String code=cell.value.toString().trim();
    String normalized=WbLanguageConstant.normalizeLanguageCode(code);
    if (normalized != null) {
      return normalized;
    }
 else {
      QAWarning issue=new QAWarning("ignored-language",null,QAWarning.Severity.WARNING,1);
      issue.setProperty("example_value",cell.value.toString());
      ctxt.addWarning(issue);
    }
  }
  throw new SkipSchemaExpressionException();
}
