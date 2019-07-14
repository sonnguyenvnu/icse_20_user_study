public static String toString(@ImageOrigin int imageOrigin){
switch (imageOrigin) {
case ImageOrigin.NETWORK:
    return "network";
case ImageOrigin.DISK:
  return "disk";
case ImageOrigin.MEMORY_ENCODED:
return "memory_encoded";
case ImageOrigin.MEMORY_BITMAP:
return "memory_bitmap";
case ImageOrigin.LOCAL:
return "local";
case ImageOrigin.UNKNOWN:
default :
return "unknown";
}
}
