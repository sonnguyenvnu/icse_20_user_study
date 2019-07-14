/** 
 * Returns true if this switch statement tests an expression having an enum type and all the constants of this type are covered by a switch case. Returns false if the type of the tested expression could not be resolved.
 */
public boolean isExhaustiveEnumSwitch(){
  ASTExpression expression=getTestedExpression();
  if (expression.getType() == null) {
    return false;
  }
  if (Enum.class.isAssignableFrom(expression.getType())) {
    @SuppressWarnings("unchecked") Set<String> constantNames=EnumUtils.getEnumMap((Class<? extends Enum>)expression.getType()).keySet();
    for (    ASTSwitchLabel label : this) {
      constantNames.remove(label.getFirstDescendantOfType(ASTName.class).getImage());
    }
    return constantNames.isEmpty();
  }
  return false;
}
