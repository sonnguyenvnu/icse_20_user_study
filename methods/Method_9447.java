@Override public View createView(Context context){
  actionBar.setBackgroundColor(Theme.ACTION_BAR_MEDIA_PICKER_COLOR);
  actionBar.setItemsBackgroundColor(Theme.ACTION_BAR_PICKER_SELECTOR_COLOR,false);
  actionBar.setTitleColor(0xffffffff);
  actionBar.setItemsColor(0xffffffff,false);
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setAllowOverlayTitle(true);
  actionBar.setTitle(LocaleController.getString("CropImage",R.string.CropImage));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        finishFragment();
      }
 else       if (id == done_button) {
        if (delegate != null && !doneButtonPressed) {
          Bitmap bitmap=view.getBitmap();
          if (bitmap == imageToCrop) {
            sameBitmap=true;
          }
          delegate.didFinishEdit(bitmap);
          doneButtonPressed=true;
        }
        finishFragment();
      }
    }
  }
);
  ActionBarMenu menu=actionBar.createMenu();
  menu.addItemWithWidth(done_button,R.drawable.ic_done,AndroidUtilities.dp(56));
  fragmentView=view=new PhotoCropView(context);
  ((PhotoCropView)fragmentView).freeform=getArguments().getBoolean("freeform",false);
  fragmentView.setLayoutParams(new FrameLayout.LayoutParams(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  return fragmentView;
}
