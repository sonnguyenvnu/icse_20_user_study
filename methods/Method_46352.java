protected boolean isSuccess(String resultCode){
  return "00".equals(resultCode) || "0".equals(resultCode) || com.alipay.sofa.rpc.common.utils.StringUtils.isBlank(resultCode);
}
