/** 
 * Creates an  {@link OAuth2Exception} from a Map&lt;String,String&gt;.
 * @param errorParams
 * @return
 */
public static OAuth2Exception valueOf(Map<String,String> errorParams){
  String errorCode=errorParams.get(ERROR);
  String errorMessage=errorParams.containsKey(DESCRIPTION) ? errorParams.get(DESCRIPTION) : null;
  OAuth2Exception ex=create(errorCode,errorMessage);
  Set<Map.Entry<String,String>> entries=errorParams.entrySet();
  for (  Map.Entry<String,String> entry : entries) {
    String key=entry.getKey();
    if (!ERROR.equals(key) && !DESCRIPTION.equals(key)) {
      ex.addAdditionalInformation(key,entry.getValue());
    }
  }
  return ex;
}
