/** 
 * {@inheritDoc}
 */
@Override protected void eval(){
  if (srcControl.get() instanceof TextInputControl) {
    evalTextInputField();
  }
  if (srcControl.get() instanceof ComboBoxBase) {
    evalComboBoxField();
  }
}
