/** 
 * ?????????
 * @param phone
 * @param verify
 * @return
 */
private zuo.biao.apijson.JSONRequest newVerifyRequest(int type,String phone,String verify){
  return new JSONRequest(new Verify(type,phone).setVerify(verify)).setTag(VERIFY_).setFormat(true);
}
