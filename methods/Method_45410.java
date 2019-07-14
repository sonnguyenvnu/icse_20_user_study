private Function<String,MountPredicate> toExclude(){
  return new Function<String,MountPredicate>(){
    @Override public MountPredicate apply(    final String input){
      return exclude(input);
    }
  }
;
}
