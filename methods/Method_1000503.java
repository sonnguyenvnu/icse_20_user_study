public Object calculate(){
  Object lval=calculateItem(this.left);
  if (null == lval)   return false;
  if (!(lval instanceof Boolean)) {
    if (!Castors.me().castTo(lval,Boolean.class)) {
      return false;
    }
  }
 else   if (!(Boolean)lval) {
    return false;
  }
  Object rval=calculateItem(this.right);
  if (null == rval)   return false;
  if (!(rval instanceof Boolean)) {
    return Castors.me().castTo(rval,Boolean.class);
  }
  return (Boolean)rval;
}
