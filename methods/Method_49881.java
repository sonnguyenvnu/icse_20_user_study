/** 
 * Resolve the macro in HTTP param value text For example, "something##LINE1##something" is resolved to "something9139531419something"
 * @param value The HTTP param value possibly containing macros
 * @return The HTTP param with macro resolved to real value
 */
private static String resolveMacro(Context context,String value,MmsConfig.Overridden mmsConfig){
  if (TextUtils.isEmpty(value)) {
    return value;
  }
  final Matcher matcher=MACRO_P.matcher(value);
  int nextStart=0;
  StringBuilder replaced=null;
  while (matcher.find()) {
    if (replaced == null) {
      replaced=new StringBuilder();
    }
    final int matchedStart=matcher.start();
    if (matchedStart > nextStart) {
      replaced.append(value.substring(nextStart,matchedStart));
    }
    final String macro=matcher.group(1);
    final String macroValue=mmsConfig.getHttpParamMacro(context,macro);
    if (macroValue != null) {
      replaced.append(macroValue);
    }
 else {
      Timber.w("HTTP: invalid macro " + macro);
    }
    nextStart=matcher.end();
  }
  if (replaced != null && nextStart < value.length()) {
    replaced.append(value.substring(nextStart));
  }
  return replaced == null ? value : replaced.toString();
}
