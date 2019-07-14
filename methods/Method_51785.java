/** 
 * Returns true if this annotation suppresses the given rule. The suppression annotation is  {@link SuppressWarnings}. This method returns true if this annotation is a SuppressWarnings, and if the set of suppressed warnings ( {@link SuppressWarnings#value()}) contains at least one of those: <ul> <li>"PMD" (suppresses all rules); <li>"PMD.rulename", where rulename is the name of the given rule; <li>"all" (conventional value to suppress all warnings). </ul> <p>Additionnally, the following values suppress a specific set of rules: <ul> <li> {@code "unused"}: suppresses rules like UnusedLocalVariable or UnusedPrivateField; <li> {@code "serial"}: suppresses BeanMembersShouldSerialize and MissingSerialVersionUID; </ul>
 * @param rule The rule for which to check for suppression
 * @return True if this annotation suppresses the given rule
 */
public boolean suppresses(Rule rule){
  if (jjtGetChild(0) instanceof ASTMarkerAnnotation) {
    return false;
  }
  if (isSuppressWarnings()) {
    for (    ASTLiteral element : findDescendantsOfType(ASTLiteral.class)) {
      if (element.hasImageEqualTo("\"PMD\"") || element.hasImageEqualTo("\"PMD." + rule.getName() + "\"") || element.hasImageEqualTo("\"all\"") || element.hasImageEqualTo("\"serial\"") && SERIAL_RULES.contains(rule.getName()) || element.hasImageEqualTo("\"unused\"") && UNUSED_RULES.contains(rule.getName())) {
        return true;
      }
    }
  }
  return false;
}
