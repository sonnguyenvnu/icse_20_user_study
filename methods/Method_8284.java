@Override public boolean extendActionMode(Menu menu){
  if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
    if (PhotoViewer.getInstance().getSelectiongLength() == 0 || menu.findItem(android.R.id.copy) == null) {
      return true;
    }
  }
 else {
    if (chatActivityEnterView.getSelectionLength() == 0 || menu.findItem(android.R.id.copy) == null) {
      return true;
    }
  }
  if (Build.VERSION.SDK_INT >= 23) {
    menu.removeItem(android.R.id.shareText);
  }
  SpannableStringBuilder stringBuilder=new SpannableStringBuilder(LocaleController.getString("Bold",R.string.Bold));
  stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),0,stringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  menu.add(R.id.menu_groupbolditalic,R.id.menu_bold,6,stringBuilder);
  stringBuilder=new SpannableStringBuilder(LocaleController.getString("Italic",R.string.Italic));
  stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")),0,stringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  menu.add(R.id.menu_groupbolditalic,R.id.menu_italic,7,stringBuilder);
  stringBuilder=new SpannableStringBuilder(LocaleController.getString("Mono",R.string.Mono));
  stringBuilder.setSpan(new TypefaceSpan(Typeface.MONOSPACE),0,stringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  menu.add(R.id.menu_groupbolditalic,R.id.menu_mono,8,stringBuilder);
  menu.add(R.id.menu_groupbolditalic,R.id.menu_link,9,LocaleController.getString("CreateLink",R.string.CreateLink));
  menu.add(R.id.menu_groupbolditalic,R.id.menu_regular,10,LocaleController.getString("Regular",R.string.Regular));
  return true;
}
