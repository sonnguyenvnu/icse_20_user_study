@Override protected void execute(){
  String retiredArg;
  String deadArg;
  if (keyStrength() == Strength.STRONG) {
    retiredArg=RETIRED_STRONG_KEY;
    deadArg=DEAD_STRONG_KEY;
  }
 else {
    retiredArg=RETIRED_WEAK_KEY;
    deadArg=DEAD_WEAK_KEY;
  }
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("isAlive").addStatement("Object key = getKeyReference()").addStatement("return (key != $L) && (key != $L)",retiredArg,deadArg).addModifiers(context.publicFinalModifiers()).returns(boolean.class).build());
  addState("isRetired","retire",retiredArg,false);
  addState("isDead","die",deadArg,true);
}
