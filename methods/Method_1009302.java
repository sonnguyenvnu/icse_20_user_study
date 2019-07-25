@NotNull private static <I extends Bodied & Parent>Resolvable resolvable(@NotNull I parentBodied){
  Body body=parentBodied.getBody();
  return resolvable(parentBodied,childNodes(body));
}
