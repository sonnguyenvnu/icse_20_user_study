public static Annotation name(String name,Consumer<Builder> annotation){
  Builder builder=new Builder(name);
  if (annotation != null) {
    annotation.accept(builder);
  }
  return new Annotation(builder);
}
