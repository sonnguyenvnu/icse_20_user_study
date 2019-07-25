private Abbreviations load(Locale locale){
  InputStream is=getResources(locale).openRawResource(R.raw.abbreviations);
  return new Abbreviations(is,locale);
}
