/** 
 * ?????
 * @param tk
 * @param tv
 * @param real
 * @throws Exception 
 */
public static void type(@NotNull String tk,Object tv,@NotNull JSONObject real) throws UnsupportedDataTypeException {
  if (tv instanceof String == false) {
    throw new UnsupportedDataTypeException("????????" + tk + ":value ?value????" + "Request?????? TYPE:{ key:value } ??value???String???");
  }
  type(tk,(String)tv,real.get(tk));
}
