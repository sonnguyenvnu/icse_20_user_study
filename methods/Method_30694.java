public static UserReviewListResource attachTo(String userIdOrUid,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  UserReviewListResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(userIdOrUid);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
