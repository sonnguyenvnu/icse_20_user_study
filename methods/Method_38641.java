/** 
 * Returns JSON violations string. Contains javascript array with elements that contain: <ul> <li>name - violation name</li> <li>msg - message code i.e. constraint class name</li> </ul>
 */
public static String createViolationsJsonString(final HttpServletRequest request,final List<Violation> violations){
  if (violations == null) {
    return StringPool.EMPTY;
  }
  StringBuilder sb=new StringBuilder().append('[');
  for (int i=0, violationsSize=violations.size(); i < violationsSize; i++) {
    Violation violation=violations.get(i);
    if (i != 0) {
      sb.append(',');
    }
    sb.append('{');
    sb.append("\"name\":\"").append(violation.getName()).append('"').append(',');
    sb.append("\"msg\":\"").append(resolveValidationMessage(request,violation)).append('"');
    sb.append('}');
  }
  sb.append(']');
  return sb.toString();
}
