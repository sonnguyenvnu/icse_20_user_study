private MutableLiveData<HotMovieBean> loadHotMovie(){
  MutableLiveData<HotMovieBean> hotMovie=oneRepo.getHotMovie();
  setHotMovieBean(hotMovie);
  return hotMovie;
}
