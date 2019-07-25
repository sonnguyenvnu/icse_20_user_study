public Object calculate(){
  Integer lval=(Integer)calculateItem(left);
  Integer rval=(Integer)calculateItem(right);
  return lval | rval;
}
