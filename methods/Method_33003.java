private void init(){
  addEventFilter(MouseEvent.MOUSE_PRESSED,click -> {
    if (getTreeTableView().getEditingCell() != null && !isEditing()) {
      OnPressedEditableTreeTableCell editingCell=(OnPressedEditableTreeTableCell)getTreeTableView().getProperties().remove(OnPressedEditableTreeTableCell.class);
      if (editingCell != null) {
        editingCell.commitHelper(true);
      }
    }
  }
);
  addEventFilter(MouseEvent.MOUSE_RELEASED,click -> {
    if (!isEmpty() && isEditable() && !isEditing() && getTableColumn().isEditable()) {
      getTreeTableView().edit(getIndex(),getTableColumn());
    }
  }
);
}
