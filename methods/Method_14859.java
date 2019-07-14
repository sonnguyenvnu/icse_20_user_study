private Intent newResult(){
  return new Intent().putExtra(RESULT_PHONE,StringUtil.getTrimedString(etPasswordPhone)).putExtra(RESULT_VERIFY,StringUtil.getTrimedString(etPasswordVerify)).putExtra(RESULT_PASSWORD,StringUtil.getTrimedString(etPasswordPassword0));
}
