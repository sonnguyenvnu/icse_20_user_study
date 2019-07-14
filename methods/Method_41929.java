@OnClick(R.id.about_link_changelog) public void onChangelog(){
  AlertDialog alertDialog=AlertDialogsHelper.showChangelogDialog(this);
  alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.ok_action).toUpperCase(),(dialogInterface,i) -> {
  }
);
  alertDialog.show();
}
