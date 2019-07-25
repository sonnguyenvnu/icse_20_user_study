/** 
 * {@inheritDoc}
 */
@SuppressWarnings("unchecked") @Override public BeanBuilder create(BeanCreationDirective directive){
  Class<? extends Message> messageClass=(Class<? extends Message>)directive.getActualClass();
  final Message.Builder protoBuilder=ProtoUtils.getBuilder(messageClass);
  return new ProtoBeanBuilder(protoBuilder,messageClass);
}
