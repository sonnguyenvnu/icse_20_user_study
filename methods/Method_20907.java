public static Comment comment(){
  return Comment.builder().author(UserFactory.user()).body("Some comment").createdAt(DateTime.now()).deletedAt(DateTime.parse("1970-01-01T00:00:00Z")).id(IdFactory.id()).build();
}
