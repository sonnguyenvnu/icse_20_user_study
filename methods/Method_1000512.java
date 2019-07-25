public Object calculate(){
  Object lval=calculateItem(left);
  if (null != lval) {
    if (!(lval instanceof Boolean)) {
      if (Castors.me().castTo(lval,Boolean.class)) {
        return true;
      }
    }
 else     if ((Boolean)lval) {
      return true;
    }
  }
  Object rval=calculateItem(right);
  if (null != rval) {
    if (!(rval instanceof Boolean)) {
      return Castors.me().castTo(rval,Boolean.class);
    }
    if ((Boolean)rval) {
      return true;
    }
  }
  return false;
}
