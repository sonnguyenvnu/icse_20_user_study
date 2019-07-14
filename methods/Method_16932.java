@Override protected void execute(){
  context.nodeSubtype.addField(newFieldOffset(context.className,"key")).addField(newKeyField()).addMethod(newGetter(keyStrength(),kTypeVar,"key",Visibility.LAZY)).addMethod(newGetRef("key"));
}
