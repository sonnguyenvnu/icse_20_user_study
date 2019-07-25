public static void arguments(SNode methodCall,final TextGenContext ctx){
  final TextGenSupport tgs=new TextGenSupport(ctx);
  tgs.append("(");
{
    Iterable<SNode> collection=SLinkOperations.getChildren(methodCall,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301aeL,"actualArgument"));
    final SNode lastItem=Sequence.fromIterable(collection).last();
    for (    SNode item : collection) {
      tgs.appendNode(item);
      if (item != lastItem) {
        tgs.append(", ");
      }
    }
  }
  tgs.append(")");
}
