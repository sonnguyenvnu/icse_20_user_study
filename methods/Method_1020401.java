/** 
 * Returns a new  {@link Builder} with default values.
 * @param type designates whether this is a network send or receive message.
 * @param messageId serves to uniquely identify each network message.
 * @return a new {@code Builder} with default values.
 * @throws NullPointerException if {@code type} is {@code null}.
 * @since 0.5
 */
public static Builder builder(Type type,long messageId){
  return new AutoValue_NetworkEvent.Builder().setType(Utils.checkNotNull(type,"type")).setMessageId(messageId).setUncompressedMessageSize(0).setCompressedMessageSize(0);
}
