@Override public void onViewAttachedToWindow(RecyclerView.ViewHolder holder){
  if (holder.itemView instanceof DialogCell) {
    DialogCell dialogCell=(DialogCell)holder.itemView;
    dialogCell.onReorderStateChanged(isReordering,false);
    int position=fixPosition(holder.getAdapterPosition());
    dialogCell.setDialogIndex(position);
    dialogCell.checkCurrentDialogIndex(dialogsListFrozen);
    dialogCell.setChecked(selectedDialogs.contains(dialogCell.getDialogId()),false);
  }
}
