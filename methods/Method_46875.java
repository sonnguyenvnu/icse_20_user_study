private void showPopup(View v,final LayoutElementParcelable rowItem){
  v.setOnClickListener(view -> {
    PopupMenu popupMenu=new ItemPopupMenu(context,mainFrag.getMainActivity(),utilsProvider,mainFrag,rowItem,view,sharedPrefs);
    popupMenu.inflate(R.menu.item_extras);
    String description=rowItem.desc.toLowerCase();
    if (rowItem.isDirectory) {
      popupMenu.getMenu().findItem(R.id.open_with).setVisible(false);
      popupMenu.getMenu().findItem(R.id.share).setVisible(false);
      if (mainFrag.getMainActivity().mReturnIntent) {
        popupMenu.getMenu().findItem(R.id.return_select).setVisible(true);
      }
    }
 else {
      popupMenu.getMenu().findItem(R.id.book).setVisible(false);
    }
    if (description.endsWith(".zip") || description.endsWith(".jar") || description.endsWith(".apk") || description.endsWith(".rar") || description.endsWith(".tar") || description.endsWith(".tar.gz"))     popupMenu.getMenu().findItem(R.id.ex).setVisible(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      if (description.endsWith(CryptUtil.CRYPT_EXTENSION))       popupMenu.getMenu().findItem(R.id.decrypt).setVisible(true);
 else       popupMenu.getMenu().findItem(R.id.encrypt).setVisible(true);
    }
    popupMenu.show();
  }
);
}
