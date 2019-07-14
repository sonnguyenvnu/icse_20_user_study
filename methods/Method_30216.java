private static MusicResource newInstance(long musicId,SimpleMusic simpleMusic,Music music){
  MusicResource instance=new MusicResource();
  instance.setArguments(musicId,simpleMusic,music);
  return instance;
}
