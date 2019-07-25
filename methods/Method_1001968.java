@Override public CString plus(int pointerMove){
  return new CString(data2,currentStart + pointerMove);
}
