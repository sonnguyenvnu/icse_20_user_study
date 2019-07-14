private static String badPositionIndex(int index,int size,String desc){
  if (index < 0) {
    return format("%s (%s) must not be negative",desc,index);
  }
 else   if (size < 0) {
    throw new IllegalArgumentException("negative size: " + size);
  }
 else {
    return format("%s (%s) must not be greater than size (%s)",desc,index,size);
  }
}
