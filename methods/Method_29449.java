/** 
 * ????????????????????????????????????
 * @param @param originalStr ?????
 * @param @param regex ?????????????????
 * @return List<String> ??????<pre> ??/1.1.1.1:sid=0x2337c7074dofj02e,37775[1](queued=0,recved=6,sent=7,sid=0x2337c7074f1102e,sdlfjle,dsfe???????? [sid=0x2337c7074dofj02e, , sid=0x2337c7074f1102e, ] </pre>
 */
public static List<String> findAllByRegex(String originalStr,String regex){
  if (StringUtil.isBlank(originalStr) || StringUtil.isBlank(regex))   return null;
  List<String> targetStrList=new ArrayList<String>();
  final Pattern patternOfTargetStr=Pattern.compile(regex,Pattern.CANON_EQ);
  final Matcher matcherOfTargetStr=patternOfTargetStr.matcher(originalStr);
  while (matcherOfTargetStr.find()) {
    targetStrList.add(StringUtil.trimToEmpty(matcherOfTargetStr.group()));
  }
  return targetStrList;
}
