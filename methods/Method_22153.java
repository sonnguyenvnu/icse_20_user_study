/** 
 * ??Gson????.
 * @param type Gson??????
 * @param typeAdapter Gson???????
 */
public static synchronized void registerTypeAdapter(final Type type,final TypeAdapter typeAdapter){
  GSON_BUILDER.registerTypeAdapter(type,typeAdapter);
  gson=GSON_BUILDER.create();
}
