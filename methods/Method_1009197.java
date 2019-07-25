@NotNull @Override public CharSequence decompile(@NotNull VirtualFile virtualFile){
  return decompiled(Optional.ofNullable(Beam.Companion.from(virtualFile)));
}
