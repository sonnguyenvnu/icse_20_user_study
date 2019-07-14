public static GameGuideListResource attachTo(long itemId,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  GameGuideListResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(itemId);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
