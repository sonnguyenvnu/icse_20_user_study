private PhotoAttachPhotoCell getCellForIndex(int index){
  int count=attachPhotoRecyclerView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=attachPhotoRecyclerView.getChildAt(a);
    if (view instanceof PhotoAttachPhotoCell) {
      PhotoAttachPhotoCell cell=(PhotoAttachPhotoCell)view;
      if ((Integer)cell.getImageView().getTag() == index) {
        return cell;
      }
    }
  }
  return null;
}
