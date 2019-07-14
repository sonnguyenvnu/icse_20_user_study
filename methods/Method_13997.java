@Override public ItemIdValue fromCell(Cell cell,ExpressionContext ctxt) throws SkipSchemaExpressionException {
  if (cell.recon != null && (Judgment.Matched.equals(cell.recon.judgment) || Judgment.New.equals(cell.recon.judgment))) {
    if (cell.recon.identifierSpace == null || !cell.recon.identifierSpace.equals(Datamodel.SITE_WIKIDATA)) {
      QAWarning warning=new QAWarning("invalid-identifier-space",null,QAWarning.Severity.INFO,1);
      warning.setProperty("example_cell",cell.value.toString());
      ctxt.addWarning(warning);
      throw new SkipSchemaExpressionException();
    }
    return new ReconItemIdValue(cell.recon,cell.value.toString());
  }
  throw new SkipSchemaExpressionException();
}
