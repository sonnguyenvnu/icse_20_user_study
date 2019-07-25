/** 
 * Initializes cell editor and its delegate.
 * @param editor actual editor component
 */
protected void initialize(final C editor){
  editorComponent=editor;
  if (editor instanceof JTextField) {
    final JTextField textField=(JTextField)editor;
    delegate=new EditorDelegate(){
      @Override public void setValue(      final Object value){
        textField.setText((value != null) ? value.toString() : "");
      }
      @Override public Object getCellEditorValue(){
        return textField.getText();
      }
    }
;
    textField.addActionListener(delegate);
  }
 else   if (editor instanceof JCheckBox) {
    final JCheckBox checkBox=(JCheckBox)editor;
    delegate=new EditorDelegate(){
      @Override public void setValue(      final Object value){
        boolean selected=false;
        if (value instanceof Boolean) {
          selected=(Boolean)value;
        }
 else         if (value instanceof String) {
          selected=value.equals("true");
        }
        checkBox.setSelected(selected);
      }
      @Override public Object getCellEditorValue(){
        return checkBox.isSelected();
      }
    }
;
    checkBox.addActionListener(delegate);
    checkBox.setRequestFocusEnabled(false);
  }
 else   if (editor instanceof JComboBox) {
    final JComboBox comboBox=(JComboBox)editor;
    if (comboBox.getUI() instanceof WebComboBoxUI) {
      final WebComboBoxUI webComboBoxUI=(WebComboBoxUI)comboBox.getUI();
      webComboBoxUI.setDrawBorder(false);
    }
    comboBox.putClientProperty(COMBOBOX_CELL_EDITOR,Boolean.TRUE);
    delegate=new EditorDelegate(){
      @Override public void setValue(      final Object value){
        comboBox.setSelectedItem(value);
      }
      @Override public Object getCellEditorValue(){
        return comboBox.getSelectedItem();
      }
      @Override public boolean shouldSelectCell(      final EventObject anEvent){
        if (anEvent instanceof MouseEvent) {
          final MouseEvent e=(MouseEvent)anEvent;
          return e.getID() != MouseEvent.MOUSE_DRAGGED;
        }
        return true;
      }
      @Override public boolean stopCellEditing(){
        if (comboBox.isEditable()) {
          comboBox.actionPerformed(new ActionEvent(WebDefaultCellEditor.this,0,""));
        }
        return super.stopCellEditing();
      }
    }
;
    comboBox.addActionListener(delegate);
  }
}
