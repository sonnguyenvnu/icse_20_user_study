@Override public boolean onMenuItemClick(MenuItem item){
  if (item.getItemId() == R.id.main_menu_about) {
    AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).setTitle(R.string.about_dialog_title).setMessage(R.string.about_dialog_message).setNeutralButton("Dismiss",null).show();
    dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#91B8CC"));
    dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setText(Html.fromHtml("<b>Dismiss</b>"));
    return true;
  }
  if (item.getItemId() == R.id.main_menu_gallery) {
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
    startActivity(intent);
    return true;
  }
  return false;
}
