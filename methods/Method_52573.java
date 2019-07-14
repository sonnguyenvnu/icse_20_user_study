@Override public boolean isRawType(){
  return typeList.length == 1 && firstJavaType().isRawType();
}
