private static int getConnectTimeout(){
  return prefStore.get(CONNECT_TIME_OUT_KEY) == null ? CONNECT_TIME_OUT_DEFAULT : Integer.parseInt((String)prefStore.get(CONNECT_TIME_OUT_KEY));
}
