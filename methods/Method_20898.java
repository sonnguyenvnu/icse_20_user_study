public static @NonNull Category bluesCategory(){
  return Category.builder().color(10878931).id(316).name("Blues").parent(musicCategory()).parentId(musicCategory().id()).position(1).projectsCount(5).slug("music/blues").build();
}
