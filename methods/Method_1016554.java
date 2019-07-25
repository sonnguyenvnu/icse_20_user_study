public static PaddedCell check(Formatter formatter,File file,String originalUnix){
  return check(Objects.requireNonNull(formatter,"formatter"),Objects.requireNonNull(file,"file"),Objects.requireNonNull(originalUnix,"originalUnix"),MAX_CYCLE);
}
