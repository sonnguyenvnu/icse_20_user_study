@NonNull @Override public ArrayList<String> getNamesToTag(){
  GistCommentsFragment view=getGistCommentsFragment();
  if (view != null)   return view.getNamesToTag();
  return new ArrayList<>();
}
