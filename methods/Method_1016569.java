public static Policy create(File projectDir,Supplier<Iterable<File>> toFormat){
  return new Policy(projectDir,toFormat);
}
