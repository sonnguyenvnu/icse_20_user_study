/** 
 * Add extra HTTP headers from mms_config.xml's httpParams, which is a list of key/value pairs separated by "|". Each key/value pair is separated by ":". Value may contain macros like "##LINE1##" or "##NAI##" which is resolved with methods in this class
 * @param connection The HttpURLConnection that we add headers to
 * @param mmsConfig The MmsConfig object
 */
private void addExtraHeaders(HttpURLConnection connection,MmsConfig.Overridden mmsConfig){
  final String extraHttpParams=mmsConfig.getHttpParams();
  if (!TextUtils.isEmpty(extraHttpParams)) {
    String paramList[]=extraHttpParams.split("\\|");
    for (    String paramPair : paramList) {
      String splitPair[]=paramPair.split(":",2);
      if (splitPair.length == 2) {
        final String name=splitPair[0].trim();
        final String value=resolveMacro(mContext,splitPair[1].trim(),mmsConfig);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
          connection.setRequestProperty(name,value);
        }
      }
    }
  }
}
