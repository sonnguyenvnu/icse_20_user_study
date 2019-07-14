static boolean isColorAttribute(TypedArray a,int idx){
synchronized (sTmpTypedValue) {
    a.getValue(idx,sTmpTypedValue);
    return sTmpTypedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT && sTmpTypedValue.type <= TypedValue.TYPE_LAST_COLOR_INT;
  }
}
