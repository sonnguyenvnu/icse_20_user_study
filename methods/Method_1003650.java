public static Handler[] unpack(Handler handler){
  if (handler instanceof ChainHandler) {
    return ((ChainHandler)handler).handlers;
  }
 else {
    return new Handler[]{handler};
  }
}
