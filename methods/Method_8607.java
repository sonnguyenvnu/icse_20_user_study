private ActionMode.Callback overrideCallback(final ActionMode.Callback callback){
  return new ActionMode.Callback(){
    @Override public boolean onCreateActionMode(    ActionMode mode,    Menu menu){
      copyPasteShowed=true;
      return callback.onCreateActionMode(mode,menu);
    }
    @Override public boolean onPrepareActionMode(    ActionMode mode,    Menu menu){
      return callback.onPrepareActionMode(mode,menu);
    }
    @Override public boolean onActionItemClicked(    ActionMode mode,    MenuItem item){
      if (item.getItemId() == R.id.menu_regular) {
        makeSelectedRegular();
        mode.finish();
        return true;
      }
 else       if (item.getItemId() == R.id.menu_bold) {
        makeSelectedBold();
        mode.finish();
        return true;
      }
 else       if (item.getItemId() == R.id.menu_italic) {
        makeSelectedItalic();
        mode.finish();
        return true;
      }
 else       if (item.getItemId() == R.id.menu_mono) {
        makeSelectedMono();
        mode.finish();
        return true;
      }
 else       if (item.getItemId() == R.id.menu_link) {
        makeSelectedUrl();
        mode.finish();
        return true;
      }
      try {
        return callback.onActionItemClicked(mode,item);
      }
 catch (      Exception ignore) {
      }
      return true;
    }
    @Override public void onDestroyActionMode(    ActionMode mode){
      copyPasteShowed=false;
      callback.onDestroyActionMode(mode);
    }
  }
;
}
