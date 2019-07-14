/** 
 * Generates a html table with violation information.
 */
private String displayRuleViolation(RuleViolation vio){
  StringBuilder sb=new StringBuilder(200);
  sb.append("<table border=\"0\">");
  renderViolationRow(sb,"Rule:",vio.getRule().getName());
  renderViolationRow(sb,"Description:",vio.getDescription());
  if (StringUtils.isNotBlank(vio.getVariableName())) {
    renderViolationRow(sb,"Variable:",vio.getVariableName());
  }
  if (vio.getEndLine() > 0) {
    renderViolationRow(sb,"Line:",vio.getEndLine() + " and " + vio.getBeginLine());
  }
 else {
    renderViolationRow(sb,"Line:",Integer.toString(vio.getBeginLine()));
  }
  sb.append("</table>");
  return sb.toString();
}
