/** 
 * ????4?y?2?y?????????????????????
 * @param argNode
 * @param data
 */
private void checkNode(Node argNode,Object data){
  String image="";
  if (argNode instanceof ASTLiteral) {
    image=argNode.getImage();
  }
  if (StringUtils.isEmpty(image) || !image.startsWith(START_QUOTE)) {
    return;
  }
  image=image.replace("\"","");
  String lowerCaseTmp=image.toLowerCase();
  if (!image.startsWith(LOW_CASE_4Y) && lowerCaseTmp.startsWith(LOW_CASE_4Y)) {
    addViolationWithMessage(data,argNode,"java.other.UseRightCaseForDateFormatRule.rule.msg",new Object[]{image});
  }
 else   if (!image.startsWith(LOW_CASE_2Y) && lowerCaseTmp.startsWith(LOW_CASE_2Y)) {
    addViolationWithMessage(data,argNode,"java.other.UseRightCaseForDateFormatRule.rule.msg",new Object[]{image});
  }
 else {
  }
}
