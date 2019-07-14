/** 
 * ?????????
 * @param phone
 * @param verify
 * @return
 */
private JSONObject newVerifyRequest(int type,String phone,String verify){
  return new JSONRequest(new Verify(type,phone).setVerify(verify)).setTag(VERIFY_);
}
