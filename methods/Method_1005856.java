public boolean equalse(SettingEnum settingEnum,String value){
  if (value == null || settingEnum == null || this.get(settingEnum.getKey()) == null || this.get(settingEnum.getKey()).getValue() == null) {
    return false;
  }
  return this.get(settingEnum.getKey()).getValue().equals(value);
}
