public void update(){
  mLocale=LangHelper.guessLocale(mContext);
  String langCode=getPreferredLocale();
  if (langCode != null && !langCode.isEmpty()) {
    mLocale=langCode;
  }
  LangHelper.forceLocale(mContext,mLocale);
}
