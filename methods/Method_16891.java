public Modifier[] protectedFinalModifiers(){
  return isFinal ? new Modifier[]{Modifier.PROTECTED} : new Modifier[]{Modifier.PROTECTED,Modifier.FINAL};
}
