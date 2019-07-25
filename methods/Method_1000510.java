public Object calculate(){
  Object rval=calculateItem(this.right);
  if (null == rval)   return true;
  if (rval instanceof Boolean) {
    return !(Boolean)rval;
  }
  return !Castors.me().castTo(rval,Boolean.class);
}
