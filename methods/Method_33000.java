private void init(){
  addEventFilter(MouseEvent.MOUSE_PRESSED,click -> {
    if (getTableView().getEditingCell() != null && !isEditing()) {
      OnPressedEditableTableCell editingCell=(OnPressedEditableTableCell)getTableView().getProperties().remove(OnPressedEditableTableCell.class);
      if (editingCell != null) {
        editingCell.commitHelper(true);
      }
    }
  }
);
  addEventFilter(MouseEvent.MOUSE_RELEASED,click -> {
    if (!isEmpty() && isEditable() && !isEditing() && getTableColumn().isEditable()) {
      getTableView().edit(getIndex(),getTableColumn());
    }
  }
);
}
