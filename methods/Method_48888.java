@Override public boolean evaluate(E element){
switch (visibility) {
case NORMAL:
    return !((InternalElement)element).isInvisible();
case SYSTEM:
  return (element instanceof JanusGraphRelation && ((JanusGraphRelation)element).getType() instanceof SystemRelationType) || (element instanceof JanusGraphVertex && element instanceof JanusGraphSchemaElement);
default :
throw new AssertionError("Unrecognized visibility: " + visibility);
}
}
