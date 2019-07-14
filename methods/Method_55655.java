public static void memPutAddress(long ptr,long value){
  if (BITS64) {
    UNSAFE.putLong(null,ptr,value);
  }
 else {
    UNSAFE.putInt(null,ptr,(int)value);
  }
}
