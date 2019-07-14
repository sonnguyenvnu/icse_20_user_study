@Override protected void onPostResume(){
  super.onPostResume();
  new Thread(() -> {
    if (Prefs.getLastVersionCode() < BuildConfig.VERSION_CODE) {
      String titleHtml=String.format(Locale.ENGLISH,"<font color='%d'>%s <b>%s</b></font>",getTextColor(),getString(R.string.changelog),BuildConfig.VERSION_NAME), buttonHtml=String.format(Locale.ENGLISH,"<font color='%d'>%s</font>",getAccentColor(),getString(R.string.view).toUpperCase());
      Snackbar snackbar=Snackbar.make(mainLayout,StringUtils.html(titleHtml),Snackbar.LENGTH_LONG).setAction(StringUtils.html(buttonHtml),view -> AlertDialogsHelper.showChangelogDialog(MainActivity.this));
      View snackbarView=snackbar.getView();
      snackbarView.setBackgroundColor(getBackgroundColor());
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)       snackbarView.setElevation(getResources().getDimension(R.dimen.snackbar_elevation));
      snackbar.show();
      Prefs.setLastVersionCode(BuildConfig.VERSION_CODE);
    }
  }
).start();
}
