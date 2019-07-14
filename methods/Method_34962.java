public void setPosts(List<Post> articlesToAdd){
  articles.clear();
  articles.addAll(articlesToAdd);
  notifyDataSetChanged();
}
