public static @NonNull Category tabletopGamesCategory(){
  return Category.builder().color(51627).id(34).name("Tabletop Games").parent(gamesCategory()).parentId(gamesCategory().id()).position(6).projectsCount(226).slug("games/tabletop games").build();
}
