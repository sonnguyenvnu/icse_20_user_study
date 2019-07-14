@Override protected RestSettingBuilder doStartRestSetting(){
  return MocoRest.delete(id());
}
