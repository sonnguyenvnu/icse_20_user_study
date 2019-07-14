/** 
 * This performs the same feature as  {@link #wrapEventHandler(String,String,Object,String)}, just parse the params from jsonObject.
 * @param subscriber Original subscriber object
 * @param jsonObject Json params
 * @return An EventHandlerWrapper wrapping a subscriber and used to registered into event bus.
 */
public static EventHandlerWrapper wrapEventHandler(@NonNull Object subscriber,@NonNull JSONObject jsonObject){
  String type=jsonObject.optString("type");
  if (TextUtils.isEmpty(type)) {
    return null;
  }
  String producer=jsonObject.optString("producer");
  String action=jsonObject.optString("action");
  return new EventHandlerWrapper(type,producer,subscriber,action);
}
