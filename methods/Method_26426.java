private boolean check(String formalName,ExpressionTree actualTree,VisitorState state){
  if (!NUMERIC_TIME_TYPE.matches(actualTree,state)) {
    return false;
  }
  String actualName=extractArgumentName(actualTree);
  if (actualName == null) {
    return false;
  }
  TimeUnit formalUnit=unitSuggestedByName(formalName);
  TimeUnit actualUnit=unitSuggestedByName(actualName);
  if (formalUnit == null || actualUnit == null || formalUnit == actualUnit) {
    return false;
  }
  String message=String.format("Possible unit mismatch: expected %s but was %s. Before accepting this change, make " + "sure that there is a true unit mismatch and not just an identifier whose name " + "contains the wrong unit. (If there is, correct that instead!)",formalUnit.toString().toLowerCase(Locale.ROOT),actualUnit.toString().toLowerCase(Locale.ROOT));
  if ((actualUnit == MICROSECONDS || actualUnit == MILLISECONDS) && (formalUnit == MICROSECONDS || formalUnit == MILLISECONDS)) {
    message+=" WARNING: This checker considers \"ms\" and \"msec\" to always refer to *milli*seconds. " + "Occasionally, code uses them for *micro*seconds. If this error involves " + "identifiers with those terms, be sure to check that it does mean milliseconds " + "before accepting this fix. If it instead means microseconds, consider renaming to " + "\"us\" or \"usec\" (or just \"micros\").";
  }
 else   if (formalUnit == SECONDS && (actualUnit != HOURS && actualUnit != DAYS)) {
    message+=" WARNING: The suggested replacement truncates fractional seconds, so a value " + "like 999ms becomes 0.";
    message+="Consider performing a floating-point division instead.";
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  fix.addStaticImport(TimeUnit.class.getName() + "." + actualUnit);
  fix.prefixWith(actualTree,String.format("%s.%s(",actualUnit,TIME_UNIT_TO_UNIT_METHODS.get(formalUnit)));
  fix.postfixWith(actualTree,")");
  state.reportMatch(buildDescription(actualTree).setMessage(message).addFix(fix.build()).build());
  return true;
}
