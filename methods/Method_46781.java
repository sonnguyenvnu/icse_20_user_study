/** 
 * Initializes the floating action button to act as to save data from an external intent
 */
private void initFabToSave(final ArrayList<Uri> uris){
  floatingActionButton.removeButton(findViewById(R.id.menu_new_folder));
  floatingActionButton.removeButton(findViewById(R.id.menu_new_file));
  floatingActionButton.removeButton(findViewById(R.id.menu_new_cloud));
  floatingActionButton.setMenuButtonIcon(R.drawable.ic_file_download_white_24dp);
  floatingActionButton.getMenuButton().setOnClickListener(v -> {
    if (uris != null && uris.size() > 0) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        File folder=new File(getCurrentMainFragment().getCurrentPath());
        int result=mainActivityHelper.checkFolder(folder,MainActivity.this);
        if (result == MainActivityHelper.WRITABLE_OR_ON_SDCARD) {
          FileUtil.writeUriToStorage(MainActivity.this,uris,getContentResolver(),getCurrentMainFragment().getCurrentPath());
          finish();
        }
 else {
          operation=DataUtils.SAVE_FILE;
          urisToBeSaved=uris;
          mainActivityHelper.checkFolder(folder,MainActivity.this);
        }
      }
 else {
        FileUtil.writeUriToStorage(MainActivity.this,uris,getContentResolver(),getCurrentMainFragment().getCurrentPath());
        Toast.makeText(MainActivity.this,getResources().getString(R.string.saving),Toast.LENGTH_LONG).show();
        finish();
      }
    }
  }
);
  floatingActionButton.setVisibility(View.VISIBLE);
  floatingActionButton.getMenuButton().show();
}
