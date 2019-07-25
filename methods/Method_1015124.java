private ByteBuffer read(long position){
  IEntry<Long,ByteBuffer> e=buffers.floor(position);
  return (ByteBuffer)e.value().duplicate().position((int)(position - e.key()));
}
