public static GistCommentsFragment newInstance(@NonNull String gistId){
  GistCommentsFragment view=new GistCommentsFragment();
  view.setArguments(Bundler.start().put("gistId",gistId).end());
  return view;
}
