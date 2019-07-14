private static int getReadTimeout(){
  return prefStore.get(READ_TIME_OUT_KEY) == null ? READ_TIME_OUT_DEFAULT : Integer.parseInt((String)prefStore.get(READ_TIME_OUT_KEY));
}
