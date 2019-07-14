/** 
 * Returns true if this switch has a  {@code default} case.
 */
public boolean hasDefaultCase(){
  for (  ASTSwitchLabel label : this) {
    if (label.isDefault()) {
      return true;
    }
  }
  return false;
}
