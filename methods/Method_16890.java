public Modifier[] publicFinalModifiers(){
  return isFinal ? new Modifier[]{Modifier.PUBLIC} : new Modifier[]{Modifier.PUBLIC,Modifier.FINAL};
}
