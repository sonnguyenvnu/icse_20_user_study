/** 
 * Registers a new generic packet handler for the given packet class. The packet class must meet the criteria for  {@link GenericHandler}; specifically, it must have an no-argument constructor and require no parsing logic outside of an invocation of {@link Packet#parse(java.nio.ByteBuffer)}.
 * @param type the type of the packet to register
 * @param clazz the class of the packet to register
 */
public final void register(int type,Class<? extends Packet> clazz){
  handlers.put(type,new GenericHandler<>(clazz));
}
