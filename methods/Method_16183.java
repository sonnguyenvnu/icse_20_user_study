public static List<ActionEntity> create(String... actions){
  return Arrays.stream(actions).map(ActionEntity::new).collect(Collectors.toList());
}
