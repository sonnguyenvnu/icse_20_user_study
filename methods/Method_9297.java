private void showLanguageAlertInternal(LocaleController.LocaleInfo systemInfo,LocaleController.LocaleInfo englishInfo,String systemLang){
  try {
    loadingLocaleDialog=false;
    boolean firstSystem=systemInfo.builtIn || LocaleController.getInstance().isCurrentLocalLocale();
    AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
    builder.setTitle(getStringForLanguageAlert(systemLocaleStrings,"ChooseYourLanguage",R.string.ChooseYourLanguage));
    builder.setSubtitle(getStringForLanguageAlert(englishLocaleStrings,"ChooseYourLanguage",R.string.ChooseYourLanguage));
    LinearLayout linearLayout=new LinearLayout(LaunchActivity.this);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    final LanguageCell[] cells=new LanguageCell[2];
    final LocaleController.LocaleInfo[] selectedLanguage=new LocaleController.LocaleInfo[1];
    final LocaleController.LocaleInfo[] locales=new LocaleController.LocaleInfo[2];
    final String englishName=getStringForLanguageAlert(systemLocaleStrings,"English",R.string.English);
    locales[0]=firstSystem ? systemInfo : englishInfo;
    locales[1]=firstSystem ? englishInfo : systemInfo;
    selectedLanguage[0]=firstSystem ? systemInfo : englishInfo;
    for (int a=0; a < 2; a++) {
      cells[a]=new LanguageCell(LaunchActivity.this,true);
      cells[a].setLanguage(locales[a],locales[a] == englishInfo ? englishName : null,true);
      cells[a].setTag(a);
      cells[a].setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector),2));
      cells[a].setLanguageSelected(a == 0);
      linearLayout.addView(cells[a],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
      cells[a].setOnClickListener(v -> {
        Integer tag=(Integer)v.getTag();
        selectedLanguage[0]=((LanguageCell)v).getCurrentLocale();
        for (int a1=0; a1 < cells.length; a1++) {
          cells[a1].setLanguageSelected(a1 == tag);
        }
      }
);
    }
    LanguageCell cell=new LanguageCell(LaunchActivity.this,true);
    cell.setValue(getStringForLanguageAlert(systemLocaleStrings,"ChooseYourLanguageOther",R.string.ChooseYourLanguageOther),getStringForLanguageAlert(englishLocaleStrings,"ChooseYourLanguageOther",R.string.ChooseYourLanguageOther));
    cell.setOnClickListener(v -> {
      localeDialog=null;
      drawerLayoutContainer.closeDrawer(true);
      presentFragment(new LanguageSelectActivity());
      if (visibleDialog != null) {
        visibleDialog.dismiss();
        visibleDialog=null;
      }
    }
);
    linearLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
    builder.setView(linearLayout);
    builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
      LocaleController.getInstance().applyLanguage(selectedLanguage[0],true,false,currentAccount);
      rebuildAllFragments(true);
    }
);
    localeDialog=showAlertDialog(builder);
    SharedPreferences preferences=MessagesController.getGlobalMainSettings();
    preferences.edit().putString("language_showed2",systemLang).commit();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
