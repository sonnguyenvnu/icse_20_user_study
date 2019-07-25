private static void zip(List<Tag> list,String... tags){
  checkArgument(tags.length % 2 == 0,"tags.length: %s (expected: even)",tags.length);
  for (int i=0; i < tags.length; ) {
    list.add(Tag.of(tags[i++],tags[i++]));
  }
}
