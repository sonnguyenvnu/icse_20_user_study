/** 
 * Prepares validation messages. Key is either validation constraint class name or violation name.
 */
public static String resolveValidationMessage(final HttpServletRequest request,final Violation violation){
  ValidationConstraint vc=violation.getConstraint();
  String key=vc != null ? vc.getClass().getName() : violation.getName();
  String msg=LocalizationUtil.findMessage(request,key);
  if (msg != null) {
    return beanTemplateParser.parseWithBean(msg,violation);
  }
  return null;
}
