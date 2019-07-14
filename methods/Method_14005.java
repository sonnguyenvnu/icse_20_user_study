public Statement evaluate(ExpressionContext ctxt,ItemIdValue subject,PropertyIdValue propertyId) throws SkipSchemaExpressionException {
  Value mainSnakValue=getMainsnak().evaluate(ctxt);
  Snak mainSnak=Datamodel.makeValueSnak(propertyId,mainSnakValue);
  List<Snak> qualifiers=new ArrayList<Snak>(getQualifiers().size());
  for (  WbSnakExpr qExpr : getQualifiers()) {
    try {
      qualifiers.add(qExpr.evaluate(ctxt));
    }
 catch (    SkipSchemaExpressionException e) {
      QAWarning warning=new QAWarning("ignored-qualifiers",null,QAWarning.Severity.INFO,1);
      warning.setProperty("example_entity",subject);
      warning.setProperty("example_property_entity",mainSnak.getPropertyId());
      ctxt.addWarning(warning);
    }
  }
  List<SnakGroup> groupedQualifiers=groupSnaks(qualifiers);
  Claim claim=Datamodel.makeClaim(subject,mainSnak,groupedQualifiers);
  List<Reference> references=new ArrayList<Reference>();
  for (  WbReferenceExpr rExpr : getReferences()) {
    try {
      references.add(rExpr.evaluate(ctxt));
    }
 catch (    SkipSchemaExpressionException e) {
      QAWarning warning=new QAWarning("ignored-references",null,QAWarning.Severity.INFO,1);
      warning.setProperty("example_entity",subject);
      warning.setProperty("example_property_entity",mainSnak.getPropertyId());
      ctxt.addWarning(warning);
    }
  }
  StatementRank rank=StatementRank.NORMAL;
  return Datamodel.makeStatement(claim,references,rank,"");
}
