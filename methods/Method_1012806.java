@Override public String[] format(Object obj){
  InfoDbData data=(InfoDbData)obj;
  return new String[]{data.imdb,data.ep_name,data.title,data.season,data.episode,data.year};
}
