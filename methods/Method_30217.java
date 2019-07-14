public static MusicResource attachTo(long musicId,SimpleMusic simpleMusic,Music music,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  MusicResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(musicId,simpleMusic,music);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
