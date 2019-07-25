private void dfs(Movie m){
  visited.add(m.movieId);
  List<Movie> movies=m.getSimilarMovies();
  for (  Movie mo : movies) {
    if (!visited.contains(mo.movieId)) {
      list.add(mo);
      dfs(mo);
    }
  }
}
