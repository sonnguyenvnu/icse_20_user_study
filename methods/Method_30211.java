public static MovieResource attachTo(long movieId,SimpleMovie simpleMovie,Movie movie,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  MovieResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(movieId,simpleMovie,movie);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
