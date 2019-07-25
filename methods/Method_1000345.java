/** 
 * ??CharSegment????????
 * @param sql sql??
 * @param value ??
 * @return ????
 * @see org.nutz.lang.segment.CharSegment
 */
public static Condition wrap(String sql,Object value){
  return Strings.isBlank(sql) ? null : new SimpleCondition(new CharSegment(sql).setBy(value));
}
