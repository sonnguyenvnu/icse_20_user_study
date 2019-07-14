/** 
 * ????-??
 * @param submitParam
 * @return
 */
public static Map<String,Object> microSubmit(RpMicroSubmitRecord submitParam){
  Map<String,Object> returnMap=new HashMap<>();
  LOG.info("????--????-??");
  Map<String,Object> certificates=WxCommonUtil.getCertificates(MCH_ID,PAY_KEY);
  certificates=JSONObject.parseObject(certificates.get("certificates").toString());
  JsonElement jsonelement=new JsonParser().parse(certificates.get("data").toString());
  JsonArray arr=jsonelement.getAsJsonArray();
  Iterator<JsonElement> it=arr.iterator();
  String associatedData=null;
  String nonce=null;
  String ciphertextEncrypt=null;
  String serialNo=null;
  while (it.hasNext()) {
    JsonElement ele=it.next();
    JsonObject o=ele.getAsJsonObject();
    serialNo=o.get("serial_no").getAsString();
    JsonObject infoEle=o.get("encrypt_certificate").getAsJsonObject();
    associatedData=infoEle.get("associated_data").getAsString();
    nonce=infoEle.get("nonce").getAsString();
    ciphertextEncrypt=infoEle.get("ciphertext").getAsString();
  }
  try {
    String ciphertext=WxCommonUtil.aesgcmDecrypt(associatedData,nonce,ciphertextEncrypt,APIV3_SECRET);
    SortedMap<String,Object> paramMap=new TreeMap<>();
    paramMap.put("version","3.0");
    paramMap.put("cert_sn",serialNo);
    paramMap.put("mch_id",MCH_ID);
    paramMap.put("nonce_str",WxCommonUtil.createNonceStr());
    paramMap.put("sign_type","HMAC-SHA256");
    paramMap.put("business_code",submitParam.getBusinessCode());
    paramMap.put("id_card_copy",submitParam.getIdCardCopy());
    paramMap.put("id_card_national",submitParam.getIdCardNational());
    paramMap.put("id_card_name",WxCommonUtil.rsaEncrypt(submitParam.getIdCardName(),ciphertext));
    paramMap.put("id_card_number",WxCommonUtil.rsaEncrypt(submitParam.getIdCardNumber(),ciphertext));
    paramMap.put("id_card_valid_time",submitParam.getIdCardValidTime());
    paramMap.put("account_name",WxCommonUtil.rsaEncrypt(submitParam.getIdCardName(),ciphertext));
    paramMap.put("account_bank",submitParam.getAccountBank());
    paramMap.put("bank_address_code",submitParam.getBankAddressCode());
    paramMap.put("account_number",WxCommonUtil.rsaEncrypt(submitParam.getAccountNumber(),ciphertext));
    paramMap.put("store_name",submitParam.getStoreName());
    paramMap.put("store_address_code",submitParam.getStoreAddressCode());
    paramMap.put("store_street",submitParam.getStoreStreet());
    paramMap.put("store_entrance_pic",submitParam.getStoreEntrancePic());
    paramMap.put("indoor_pic",submitParam.getIndoorPic());
    paramMap.put("merchant_shortname",submitParam.getMerchantShortname());
    paramMap.put("service_phone",submitParam.getServicePhone());
    paramMap.put("product_desc",submitParam.getProductDesc());
    paramMap.put("rate",submitParam.getRate());
    paramMap.put("contact",WxCommonUtil.rsaEncrypt(submitParam.getIdCardName(),ciphertext));
    paramMap.put("contact_phone",WxCommonUtil.rsaEncrypt(submitParam.getContactPhone(),ciphertext));
    paramMap.put("sign",WxCommonUtil.sha256Sign(paramMap,PAY_KEY));
    String data=WxCommonUtil.mapToXml(paramMap);
    String returnStr=WxCommonUtil.requestPostSSL(MCH_ID,KEY_STORE_URL,data,MICRO_SUBMIT_URL);
    returnMap=WxCommonUtil.xmlToMap(returnStr);
  }
 catch (  Exception e) {
    returnMap.put("return_msg",e.getMessage());
    e.printStackTrace();
    LOG.error(e.getMessage());
  }
  return returnMap;
}
