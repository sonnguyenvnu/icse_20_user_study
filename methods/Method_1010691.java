@NotNull public String serialize(@Nullable MacroProcessor mp){
  return myFsId + FS_DELIM + (mp == null ? myPath : mp.shrinkPath(myPath));
}
