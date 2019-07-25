/** 
 * ?????????,????????? ????
 * @param tClass
 * @param iServiceLoad
 * @param < T >
 */
public static <T>void register(@NonNull Class<T> tClass,@NonNull IServiceLoad<? extends T> iServiceLoad){
  map.put(tClass,iServiceLoad);
}
