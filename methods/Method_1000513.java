public Object calculate(){
  Object lval=calculateItem(left);
  if (Lang.eleSize(lval) > 0) {
    return lval;
  }
  return calculateItem(right);
}
