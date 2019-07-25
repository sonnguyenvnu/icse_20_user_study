/** 
 * {@inheritDoc}
 */
@Override public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
  return frame.isShowing() ? Boolean.TRUE : Boolean.FALSE;
}
