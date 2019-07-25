@Override public void print(Component component){
  this.player.sendMessage(ITextComponent.Serializer.fromJson(GsonComponentSerializer.INSTANCE.serialize(component)));
}
