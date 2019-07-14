/** 
 * ????????
 * @return result
 */
public boolean hasGroup(){
  return Objects.nonNull(fields) && fields.containsKey(TracingConstants.GROUP_ID) && StringUtils.hasText(fields.get(TracingConstants.GROUP_ID));
}
