private void showAttachmentError(){
  if (getParentActivity() == null) {
    return;
  }
  Toast toast=Toast.makeText(getParentActivity(),LocaleController.getString("UnsupportedAttachment",R.string.UnsupportedAttachment),Toast.LENGTH_SHORT);
  toast.show();
}
