private PhotoPickerPhotoCell getCellForIndex(int index){
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=listView.getChildAt(a);
    if (view instanceof PhotoPickerPhotoCell) {
      PhotoPickerPhotoCell cell=(PhotoPickerPhotoCell)view;
      int num=(Integer)cell.photoImage.getTag();
      if (selectedAlbum != null) {
        if (num < 0 || num >= selectedAlbum.photos.size()) {
          continue;
        }
      }
 else {
        ArrayList<MediaController.SearchImage> array;
        if (searchResult.isEmpty() && lastSearchString == null) {
          array=recentImages;
        }
 else {
          array=searchResult;
        }
        if (num < 0 || num >= array.size()) {
          continue;
        }
      }
      if (num == index) {
        return cell;
      }
    }
  }
  return null;
}
