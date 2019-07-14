public static String toString(@ImageLoadStatus int imageLoadStatus){
switch (imageLoadStatus) {
case ImageLoadStatus.REQUESTED:
    return "requested";
case ImageLoadStatus.ORIGIN_AVAILABLE:
  return "origin_available";
case ImageLoadStatus.SUCCESS:
return "success";
case ImageLoadStatus.CANCELED:
return "canceled";
case ImageLoadStatus.INTERMEDIATE_AVAILABLE:
return "intermediate_available";
case ImageLoadStatus.ERROR:
return "error";
default :
return "unknown";
}
}
