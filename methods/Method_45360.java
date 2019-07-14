protected final <T extends BaseResourceSetting>T asBaseResourceSetting(final T setting){
  BaseResourceSetting base=setting;
  base.text=text;
  base.file=file;
  base.pathResource=pathResource;
  base.json=json;
  return setting;
}
