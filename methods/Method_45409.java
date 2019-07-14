private Function<String,MountPredicate> toInclude(){
  return new Function<String,MountPredicate>(){
    @Override public MountPredicate apply(    final String input){
      return include(input);
    }
  }
;
}
