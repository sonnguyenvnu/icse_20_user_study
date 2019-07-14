private void updateRowsSelection(){
  for (int i=0; i < mediaPages.length; i++) {
    int count=mediaPages[i].listView.getChildCount();
    for (int a=0; a < count; a++) {
      View child=mediaPages[i].listView.getChildAt(a);
      if (child instanceof SharedDocumentCell) {
        ((SharedDocumentCell)child).setChecked(false,true);
      }
 else       if (child instanceof SharedPhotoVideoCell) {
        for (int b=0; b < 6; b++) {
          ((SharedPhotoVideoCell)child).setChecked(b,false,true);
        }
      }
 else       if (child instanceof SharedLinkCell) {
        ((SharedLinkCell)child).setChecked(false,true);
      }
 else       if (child instanceof SharedAudioCell) {
        ((SharedAudioCell)child).setChecked(false,true);
      }
    }
  }
}
