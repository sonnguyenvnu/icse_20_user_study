public static AlertDialog.Builder createLanguageAlert(LaunchActivity activity,final TLRPC.TL_langPackLanguage language){
  if (language == null) {
    return null;
  }
  language.lang_code=language.lang_code.replace('-','_').toLowerCase();
  language.plural_code=language.plural_code.replace('-','_').toLowerCase();
  if (language.base_lang_code != null) {
    language.base_lang_code=language.base_lang_code.replace('-','_').toLowerCase();
  }
  SpannableStringBuilder spanned;
  AlertDialog.Builder builder=new AlertDialog.Builder(activity);
  LocaleController.LocaleInfo currentInfo=LocaleController.getInstance().getCurrentLocaleInfo();
  String str;
  if (currentInfo.shortName.equals(language.lang_code)) {
    builder.setTitle(LocaleController.getString("Language",R.string.Language));
    str=LocaleController.formatString("LanguageSame",R.string.LanguageSame,language.name);
    builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),null);
    builder.setNeutralButton(LocaleController.getString("SETTINGS",R.string.SETTINGS),(dialog,which) -> activity.presentFragment(new LanguageSelectActivity()));
  }
 else {
    if (language.strings_count == 0) {
      builder.setTitle(LocaleController.getString("LanguageUnknownTitle",R.string.LanguageUnknownTitle));
      str=LocaleController.formatString("LanguageUnknownCustomAlert",R.string.LanguageUnknownCustomAlert,language.name);
      builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),null);
    }
 else {
      builder.setTitle(LocaleController.getString("LanguageTitle",R.string.LanguageTitle));
      if (language.official) {
        str=LocaleController.formatString("LanguageAlert",R.string.LanguageAlert,language.name,(int)Math.ceil(language.translated_count / (float)language.strings_count * 100));
      }
 else {
        str=LocaleController.formatString("LanguageCustomAlert",R.string.LanguageCustomAlert,language.name,(int)Math.ceil(language.translated_count / (float)language.strings_count * 100));
      }
      builder.setPositiveButton(LocaleController.getString("Change",R.string.Change),(dialogInterface,i) -> {
        String key;
        if (language.official) {
          key="remote_" + language.lang_code;
        }
 else {
          key="unofficial_" + language.lang_code;
        }
        LocaleController.LocaleInfo localeInfo=LocaleController.getInstance().getLanguageFromDict(key);
        if (localeInfo == null) {
          localeInfo=new LocaleController.LocaleInfo();
          localeInfo.name=language.native_name;
          localeInfo.nameEnglish=language.name;
          localeInfo.shortName=language.lang_code;
          localeInfo.baseLangCode=language.base_lang_code;
          localeInfo.pluralLangCode=language.plural_code;
          localeInfo.isRtl=language.rtl;
          if (language.official) {
            localeInfo.pathToFile="remote";
          }
 else {
            localeInfo.pathToFile="unofficial";
          }
        }
        LocaleController.getInstance().applyLanguage(localeInfo,true,false,false,true,UserConfig.selectedAccount);
        activity.rebuildAllFragments(true);
      }
);
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    }
  }
  spanned=new SpannableStringBuilder(AndroidUtilities.replaceTags(str));
  int start=TextUtils.indexOf(spanned,'[');
  int end;
  if (start != -1) {
    end=TextUtils.indexOf(spanned,']',start + 1);
    if (start != -1 && end != -1) {
      spanned.delete(end,end + 1);
      spanned.delete(start,start + 1);
    }
  }
 else {
    end=-1;
  }
  if (start != -1 && end != -1) {
    spanned.setSpan(new URLSpanNoUnderline(language.translations_url){
      @Override public void onClick(      View widget){
        builder.getDismissRunnable().run();
        super.onClick(widget);
      }
    }
,start,end - 1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  final TextView message=new TextView(activity);
  message.setText(spanned);
  message.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  message.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
  message.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection));
  message.setPadding(AndroidUtilities.dp(23),0,AndroidUtilities.dp(23),0);
  message.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
  message.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  builder.setView(message);
  return builder;
}
