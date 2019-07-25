public static Text convert(Component component){
  return TextSerializers.JSON.deserialize(GsonComponentSerializer.INSTANCE.serialize(component));
}
