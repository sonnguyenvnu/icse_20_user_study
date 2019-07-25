@Override protected void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out) throws Exception {
  int size=DefinedPacket.readVarInt(in);
  if (size == 0) {
    out.add(in.slice().retain());
    in.skipBytes(in.readableBytes());
  }
 else {
    ByteBuf decompressed=ctx.alloc().directBuffer();
    try {
      zlib.process(in,decompressed);
      Preconditions.checkState(decompressed.readableBytes() == size,"Decompressed packet size mismatch");
      out.add(decompressed);
      decompressed=null;
    }
  finally {
      if (decompressed != null) {
        decompressed.release();
      }
    }
  }
}
