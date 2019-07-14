public static boolean isCard(JSONObject cellData){
  if (cellData != null) {
    String type=cellData.optString("type");
switch (type) {
case TangramBuilder.TYPE_CONTAINER_BANNER:
case TangramBuilder.TYPE_CONTAINER_SCROLL:
case TangramBuilder.TYPE_CONTAINER_FLOW:
case TangramBuilder.TYPE_CONTAINER_1C_FLOW:
case TangramBuilder.TYPE_CONTAINER_2C_FLOW:
case TangramBuilder.TYPE_CONTAINER_3C_FLOW:
case TangramBuilder.TYPE_CONTAINER_4C_FLOW:
case TangramBuilder.TYPE_CONTAINER_5C_FLOW:
      return true;
default :
    return false;
}
}
 else {
return false;
}
}
