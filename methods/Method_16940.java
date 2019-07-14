@Override protected void execute(){
  context.nodeSubtype.addField(newFieldOffset(context.className,"value")).addField(newValueField()).addMethod(newGetter(valueStrength(),vTypeVar,"value",Visibility.LAZY)).addMethod(newGetRef("value")).addMethod(makeSetValue()).addMethod(makeContainsValue());
}
