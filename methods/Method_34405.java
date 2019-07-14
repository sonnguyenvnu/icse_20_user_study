/** 
 * Get the verification key for the token signatures.
 * @return the key used to verify tokens
 */
public Map<String,String> getKey(){
  Map<String,String> result=new LinkedHashMap<String,String>();
  result.put("alg",signer.algorithm());
  result.put("value",verifierKey);
  return result;
}
