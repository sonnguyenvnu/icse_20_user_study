public static <T>T tryGet(Object[] args,int index,Class<T> clz,T defaultValue){
  if (args.length > index) {
    return tryCast(args[index],clz);
  }
  return defaultValue;
}
