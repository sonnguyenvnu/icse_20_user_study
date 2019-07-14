private void addState(String checkName,String actionName,String arg,boolean finalized){
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder(checkName).addStatement("return (getKeyReference() == $L)",arg).addModifiers(context.publicFinalModifiers()).returns(boolean.class).build());
  MethodSpec.Builder action=MethodSpec.methodBuilder(actionName).addModifiers(context.publicFinalModifiers());
  if (keyStrength() != Strength.STRONG) {
    action.addStatement("(($T<K>) getKeyReference()).clear()",Reference.class);
  }
  if (valueStrength() == Strength.STRONG) {
    if (finalized) {
      action.addStatement("$T.UNSAFE.putObject(this, $N, null)",UNSAFE_ACCESS,offsetName("value"));
    }
  }
 else {
    action.addStatement("(($T<V>) getValueReference()).clear()",Reference.class);
  }
  action.addStatement("$T.UNSAFE.putObject(this, $N, $N)",UNSAFE_ACCESS,offsetName("key"),arg);
  context.nodeSubtype.addMethod(action.build());
}
