private void explainWhere(List<String> codes,Where where) throws SqlParseException {
  if (where instanceof Condition) {
    Condition condition=(Condition)where;
    if (condition.getValue() instanceof ScriptFilter) {
      codes.add(String.format("Function.identity().compose((o)->{%s}).apply(null)",((ScriptFilter)condition.getValue()).getScript()));
    }
 else     if (condition.getOpear() == OPEAR.BETWEEN) {
      Object[] objs=(Object[])condition.getValue();
      codes.add("(" + "doc['" + condition.getName() + "'].value >= " + objs[0] + " && doc['" + condition.getName() + "'].value <=" + objs[1] + ")");
    }
 else     if (condition.getOpear() == OPEAR.IN) {
      codes.add(parseInNotInJudge(condition,"==","||",false));
    }
 else     if (condition.getOpear() == OPEAR.NIN) {
      codes.add(parseInNotInJudge(condition,"!=","&&",false));
    }
 else {
      SQLExpr nameExpr=condition.getNameExpr();
      SQLExpr valueExpr=condition.getValueExpr();
      if (valueExpr instanceof SQLNullExpr) {
        codes.add("(" + "doc['" + nameExpr.toString() + "']" + ".empty)");
      }
 else {
        codes.add("(" + Util.getScriptValueWithQuote(nameExpr,"'") + condition.getOpertatorSymbol() + Util.getScriptValueWithQuote(valueExpr,"'") + ")");
      }
    }
  }
 else {
    for (    Where subWhere : where.getWheres()) {
      List<String> subCodes=new ArrayList<String>();
      explainWhere(subCodes,subWhere);
      String relation=subWhere.getConn().name().equals("AND") ? "&&" : "||";
      codes.add(Joiner.on(relation).join(subCodes));
    }
  }
}
