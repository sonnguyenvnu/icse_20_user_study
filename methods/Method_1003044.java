@Override public int update(){
  Value v=expression.getValue(session);
  int type=v.getValueType();
switch (type) {
case Value.RESULT_SET:
    return super.update();
case Value.UNKNOWN:
case Value.NULL:
  return 0;
default :
return v.getInt();
}
}
