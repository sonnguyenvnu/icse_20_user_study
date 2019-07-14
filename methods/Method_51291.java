/** 
 * Creates a new rule context, initialized with a new, empty report.
 * @param sourceCodeFilename the source code filename
 * @param sourceCodeFile the source code file
 * @return the rule context
 */
public static RuleContext newRuleContext(String sourceCodeFilename,File sourceCodeFile){
  RuleContext context=new RuleContext();
  context.setSourceCodeFile(sourceCodeFile);
  context.setSourceCodeFilename(sourceCodeFilename);
  context.setReport(new Report());
  return context;
}
