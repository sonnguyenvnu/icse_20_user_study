/** 
 * Setting this to true will disable this  {@link JFXToggleNode} from showing focus when it receives keyboard focus.
 * @param disabled True to disable visual focus and false to enable it.
 */
public final void setDisableVisualFocus(final Boolean disabled){
  this.disableVisualFocusProperty().set(disabled);
}
