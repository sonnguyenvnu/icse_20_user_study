/** 
 * Sets the target of a  {@link WebvttCssStyle} by splitting a selector of the form{@code ::cue(tag#id.class1.class2[voice="someone"]}, where every element is optional.
 */
private void applySelectorToStyle(WebvttCssStyle style,String selector){
  if ("".equals(selector)) {
    return;
  }
  int voiceStartIndex=selector.indexOf('[');
  if (voiceStartIndex != -1) {
    Matcher matcher=VOICE_NAME_PATTERN.matcher(selector.substring(voiceStartIndex));
    if (matcher.matches()) {
      style.setTargetVoice(matcher.group(1));
    }
    selector=selector.substring(0,voiceStartIndex);
  }
  String[] classDivision=Util.split(selector,"\\.");
  String tagAndIdDivision=classDivision[0];
  int idPrefixIndex=tagAndIdDivision.indexOf('#');
  if (idPrefixIndex != -1) {
    style.setTargetTagName(tagAndIdDivision.substring(0,idPrefixIndex));
    style.setTargetId(tagAndIdDivision.substring(idPrefixIndex + 1));
  }
 else {
    style.setTargetTagName(tagAndIdDivision);
  }
  if (classDivision.length > 1) {
    style.setTargetClasses(Arrays.copyOfRange(classDivision,1,classDivision.length));
  }
}
