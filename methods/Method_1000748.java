/** 
 * @param req ????
 * @param resp ????
 * @param refer ?????????????????????????? request ??????
 * @return ???
 */
@SuppressWarnings("unchecked") public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  if (null != refer)   if (refer instanceof Map<?,?>) {
    Object value=((Map<?,?>)refer).get(name);
    if (value == null) {
      return fromReqParam(req);
    }
    if ((value instanceof Collection<?>) && null != paramTypes && paramTypes.length > 0) {
      try {
        Collection<?> col=((Collection<?>)value);
        Collection<Object> nw=col.getClass().newInstance();
        Class<?> eleType=Lang.getTypeClass(paramTypes[0]);
        for (        Object ele : col) {
          Object obj=Castors.me().castTo(ele,eleType);
          nw.add(obj);
        }
        value=nw;
      }
 catch (      Exception e) {
        throw Lang.wrapThrow(e);
      }
    }
    return Castors.me().castTo(value,klass);
  }
 else {
    return Castors.me().castTo(refer,klass);
  }
  return fromReqParam(req);
}
