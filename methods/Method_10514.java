private void getPhoneInfo(){
  if (RxDeviceTool.isPhone(mContext)) {
    mTvDeviceIsPhone.setText("?");
  }
 else {
    mTvDeviceIsPhone.setText("?");
  }
  mTvDeviceSoftwarePhoneType.setText(RxDeviceTool.getPhoneType(mContext) + "");
  mTvDeviceDensity.setText(RxDeviceTool.getScreenDensity(mContext) + "");
  mTvDeviceManuFacturer.setText(RxDeviceTool.getUniqueSerialNumber() + "");
  mTvDeviceWidth.setText(RxDeviceTool.getScreenWidth(mContext) + " ");
  mTvDeviceHeight.setText(RxDeviceTool.getScreenHeight(mContext) + " ");
  mTvDeviceVersionName.setText(RxDeviceTool.getAppVersionName(mContext) + "");
  mTvDeviceVersionCode.setText(RxDeviceTool.getAppVersionNo(mContext) + "");
  mTvDeviceImei.setText(RxDeviceTool.getDeviceIdIMEI(mContext) + "");
  mTvDeviceImsi.setText(RxDeviceTool.getIMSI(mContext) + "");
  mTvDeviceSoftwareVersion.setText(RxDeviceTool.getDeviceSoftwareVersion(mContext) + "");
  mTvDeviceMac.setText(RxDeviceTool.getMacAddress(mContext));
  mTvDeviceSoftwareMccMnc.setText(RxDeviceTool.getNetworkOperator(mContext) + "");
  mTvDeviceSoftwareMccMncName.setText(RxDeviceTool.getNetworkOperatorName(mContext) + "");
  mTvDeviceSoftwareSimCountryIso.setText(RxDeviceTool.getNetworkCountryIso(mContext) + "");
  mTvDeviceSimOperator.setText(RxDeviceTool.getSimOperator(mContext) + "");
  mTvDeviceSimSerialNumber.setText(RxDeviceTool.getSimSerialNumber(mContext) + "");
  mTvDeviceSimState.setText(RxDeviceTool.getSimState(mContext) + "");
  mTvDeviceSimOperatorName.setText(RxDeviceTool.getSimOperatorName(mContext) + "");
  mTvDeviceSubscriberId.setText(RxDeviceTool.getSubscriberId(mContext) + "");
  mTvDeviceVoiceMailNumber.setText(RxDeviceTool.getVoiceMailNumber(mContext) + "");
  mTvDeviceAdnroidId.setText(RxDeviceTool.getAndroidId(mContext) + "");
  mTvDeviceBuildBrandModel.setText(RxDeviceTool.getBuildBrandModel() + "");
  mTvDeviceBuildManuFacturer.setText(RxDeviceTool.getBuildMANUFACTURER() + "");
  mTvDeviceBuildBrand.setText(RxDeviceTool.getBuildBrand() + "");
  mTvDeviceSerialNumber.setText(RxDeviceTool.getSerialNumber() + "");
  mTvDeviceIso.setText(RxDeviceTool.getNetworkCountryIso(mContext) + "");
  mTvDevicePhone.setText(RxDeviceTool.getLine1Number(mContext) + "");
}
