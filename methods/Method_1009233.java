private static void version(@NotNull StringBuilder stringBuilder,@Nullable String version){
  if (version != null) {
    header(stringBuilder,1,"Version");
    stringBuilder.append(version);
    stringBuilder.append("\n\n");
  }
}
