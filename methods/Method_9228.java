private void updateVisibleRows(int mask){
  if (listView == null || dialogsListFrozen) {
    return;
  }
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof DialogCell) {
      if (listView.getAdapter() != dialogsSearchAdapter) {
        DialogCell cell=(DialogCell)child;
        if ((mask & MessagesController.UPDATE_MASK_REORDER) != 0) {
          cell.onReorderStateChanged(actionBar.isActionModeShowed(),true);
        }
        if ((mask & MessagesController.UPDATE_MASK_CHECK) != 0) {
          cell.setChecked(false,(mask & MessagesController.UPDATE_MASK_CHAT) != 0);
        }
 else {
          if ((mask & MessagesController.UPDATE_MASK_NEW_MESSAGE) != 0) {
            cell.checkCurrentDialogIndex(dialogsListFrozen);
            if (dialogsType == 0 && AndroidUtilities.isTablet()) {
              cell.setDialogSelected(cell.getDialogId() == openedDialogId);
            }
          }
 else           if ((mask & MessagesController.UPDATE_MASK_SELECT_DIALOG) != 0) {
            if (dialogsType == 0 && AndroidUtilities.isTablet()) {
              cell.setDialogSelected(cell.getDialogId() == openedDialogId);
            }
          }
 else {
            cell.update(mask);
          }
          ArrayList<Long> selectedDialogs=dialogsAdapter.getSelectedDialogs();
          if (selectedDialogs != null) {
            cell.setChecked(selectedDialogs.contains(cell.getDialogId()),false);
          }
        }
      }
    }
 else     if (child instanceof UserCell) {
      ((UserCell)child).update(mask);
    }
 else     if (child instanceof ProfileSearchCell) {
      ((ProfileSearchCell)child).update(mask);
    }
 else     if (child instanceof RecyclerListView) {
      RecyclerListView innerListView=(RecyclerListView)child;
      int count2=innerListView.getChildCount();
      for (int b=0; b < count2; b++) {
        View child2=innerListView.getChildAt(b);
        if (child2 instanceof HintDialogCell) {
          ((HintDialogCell)child2).update(mask);
        }
      }
    }
  }
}
