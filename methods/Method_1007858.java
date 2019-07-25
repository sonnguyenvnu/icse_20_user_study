@Override public void print(Component component){
  this.player.sendMessage(Text.Serializer.fromJson(GsonComponentSerializer.INSTANCE.serialize(component)));
}
