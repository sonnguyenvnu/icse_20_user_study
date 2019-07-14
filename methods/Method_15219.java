/** 
 * ?????
 * @param isTest
 */
private void saveAndExit(boolean isTest){
  if (StringUtil.isNotEmpty(sharedPreferencesPath,true) && StringUtil.isNotEmpty(isTest ? testKey : normalKey,true)) {
    SettingUtil.putBoolean(SettingUtil.KEY_IS_ON_TEST_MODE,isTest);
    DataKeeper.save(sharedPreferencesPath,pathMode,isTest ? testKey : normalKey,StringUtil.getNoBlankString(isTest ? etServerSettingTest : etServerSettingNormal));
    showShortToast("???????" + SERVER_NAMES[SettingUtil.isOnTestMode ? 1 : 0] + "??????????????");
  }
  setResult(RESULT_OK,new Intent().putExtra(isTest ? RESULT_TEST_ADDRESS : RESULT_NORMAL_ADDRESS,StringUtil.getNoBlankString(isTest ? etServerSettingTest : etServerSettingNormal)));
  finish();
}
