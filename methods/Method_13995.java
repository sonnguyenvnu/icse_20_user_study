@Override public TimeValue fromCell(Cell cell,ExpressionContext ctxt) throws SkipSchemaExpressionException {
  try {
    return WbDateConstant.parse(cell.value.toString());
  }
 catch (  ParseException e) {
    if (!cell.value.toString().isEmpty()) {
      QAWarning issue=new QAWarning("ignored-date",null,QAWarning.Severity.WARNING,1);
      issue.setProperty("example_value",cell.value.toString());
      ctxt.addWarning(issue);
    }
    throw new SkipSchemaExpressionException();
  }
}
