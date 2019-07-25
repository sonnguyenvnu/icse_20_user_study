public Object create(String[] args,int off){
  InfoDbData data=new InfoDbData();
  data.imdb=FileDb.safeGetArg(args,off);
  data.ep_name=FileDb.safeGetArg(args,off + 1);
  data.title=FileDb.safeGetArg(args,off + 2);
  data.season=FileDb.safeGetArg(args,off + 3);
  data.episode=FileDb.safeGetArg(args,off + 4);
  data.year=FileDb.safeGetArg(args,off + 5);
  return data;
}
