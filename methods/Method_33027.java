/** 
 * Indicates whether or not this  {@link JFXCheckBox} will show focus when it receives keyboard focus.
 * @return False if this {@link JFXCheckBox} will show visual focus and true if it will not.
 */
public final Boolean isDisableVisualFocus(){
  return disableVisualFocus != null && this.disableVisualFocusProperty().get();
}
