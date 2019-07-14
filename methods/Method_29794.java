public static ApiV2BroadcastListResource attachTo(String userIdOrUid,String topic,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  ApiV2BroadcastListResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(userIdOrUid,topic);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
