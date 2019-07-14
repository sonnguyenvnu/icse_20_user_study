public void openMenu(boolean hasAvatar,Runnable onDeleteAvatar){
  if (parentFragment == null || parentFragment.getParentActivity() == null) {
    return;
  }
  BottomSheet.Builder builder=new BottomSheet.Builder(parentFragment.getParentActivity());
  builder.setTitle(LocaleController.getString("ChoosePhoto",R.string.ChoosePhoto));
  CharSequence[] items;
  int[] icons;
  if (searchAvailable) {
    if (hasAvatar) {
      items=new CharSequence[]{LocaleController.getString("ChooseTakePhoto",R.string.ChooseTakePhoto),LocaleController.getString("ChooseFromGallery",R.string.ChooseFromGallery),LocaleController.getString("ChooseFromSearch",R.string.ChooseFromSearch),LocaleController.getString("DeletePhoto",R.string.DeletePhoto)};
      icons=new int[]{R.drawable.menu_camera,R.drawable.profile_photos,R.drawable.menu_search,R.drawable.chats_delete};
    }
 else {
      items=new CharSequence[]{LocaleController.getString("ChooseTakePhoto",R.string.ChooseTakePhoto),LocaleController.getString("ChooseFromGallery",R.string.ChooseFromGallery),LocaleController.getString("ChooseFromSearch",R.string.ChooseFromSearch)};
      icons=new int[]{R.drawable.menu_camera,R.drawable.profile_photos,R.drawable.menu_search};
    }
  }
 else {
    if (hasAvatar) {
      items=new CharSequence[]{LocaleController.getString("ChooseTakePhoto",R.string.ChooseTakePhoto),LocaleController.getString("ChooseFromGallery",R.string.ChooseFromGallery),LocaleController.getString("DeletePhoto",R.string.DeletePhoto)};
      icons=new int[]{R.drawable.menu_camera,R.drawable.profile_photos,R.drawable.chats_delete};
    }
 else {
      items=new CharSequence[]{LocaleController.getString("ChooseTakePhoto",R.string.ChooseTakePhoto),LocaleController.getString("ChooseFromGallery",R.string.ChooseFromGallery)};
      icons=new int[]{R.drawable.menu_camera,R.drawable.profile_photos};
    }
  }
  builder.setItems(items,icons,(dialogInterface,i) -> {
    if (i == 0) {
      openCamera();
    }
 else     if (i == 1) {
      openGallery();
    }
 else     if (searchAvailable && i == 2) {
      openSearch();
    }
 else     if (searchAvailable && i == 3 || i == 2) {
      onDeleteAvatar.run();
    }
  }
);
  BottomSheet sheet=builder.create();
  parentFragment.showDialog(sheet);
  TextView titleView=sheet.getTitleView();
  if (titleView != null) {
    titleView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
    titleView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  }
  sheet.setItemColor(searchAvailable ? 3 : 2,Theme.getColor(Theme.key_dialogTextRed2),Theme.getColor(Theme.key_dialogRedIcon));
}
