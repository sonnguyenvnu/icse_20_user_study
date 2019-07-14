public static GameResource attachTo(long gameId,SimpleGame simpleGame,Game game,Fragment fragment,String tag,int requestCode){
  FragmentActivity activity=fragment.getActivity();
  GameResource instance=FragmentUtils.findByTag(activity,tag);
  if (instance == null) {
    instance=newInstance(gameId,simpleGame,game);
    FragmentUtils.add(instance,activity,tag);
  }
  instance.setTarget(fragment,requestCode);
  return instance;
}
