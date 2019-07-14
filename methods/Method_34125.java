/** 
 * @return a comma-delimited list of details (key=value pairs)
 */
public String getSummary(){
  StringBuilder builder=new StringBuilder();
  String delim="";
  String error=this.getOAuth2ErrorCode();
  if (error != null) {
    builder.append(delim).append("error=\"").append(error).append("\"");
    delim=", ";
  }
  String errorMessage=this.getMessage();
  if (errorMessage != null) {
    builder.append(delim).append("error_description=\"").append(errorMessage).append("\"");
    delim=", ";
  }
  Map<String,String> additionalParams=this.getAdditionalInformation();
  if (additionalParams != null) {
    for (    Map.Entry<String,String> param : additionalParams.entrySet()) {
      builder.append(delim).append(param.getKey()).append("=\"").append(param.getValue()).append("\"");
      delim=", ";
    }
  }
  return builder.toString();
}
