private static void header(@NotNull StringBuilder stringBuilder,int level,@NotNull String name){
  stringBuilder.append("\n");
  for (int i=0; i < level; i++) {
    stringBuilder.append('#');
  }
  stringBuilder.append(' ');
  stringBuilder.append(name);
  stringBuilder.append("\n\n");
}
