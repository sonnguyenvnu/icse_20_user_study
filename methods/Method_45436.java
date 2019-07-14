private static Iterable<InputStream> toInputStreams(final Iterable<File> files){
  return FluentIterable.from(files).transform(new Function<File,InputStream>(){
    @Override public InputStream apply(    final File input){
      return toInputStream(input);
    }
  }
);
}
