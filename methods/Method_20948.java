@Override public @NonNull Observable<List<Category>> fetchCategories(){
  return Observable.just(Arrays.asList(CategoryFactory.artCategory(),CategoryFactory.bluesCategory(),CategoryFactory.ceramicsCategory(),CategoryFactory.gamesCategory(),CategoryFactory.musicCategory(),CategoryFactory.photographyCategory(),CategoryFactory.tabletopGamesCategory(),CategoryFactory.textilesCategory(),CategoryFactory.worldMusicCategory()));
}
