private String format(final ChannelHandlerContext ctx,final String eventName,final ByteBuf arg) throws IOException {
  final int readableBytes=arg.readableBytes();
  if (readableBytes == 0) {
    return super.format(ctx,eventName,arg);
  }
 else   if (readableBytes >= 2) {
    final StringBuilder sb=new StringBuilder();
    sb.append(ctx.channel().toString());
    final int offset=arg.readerIndex();
    if (arg.getByte(offset) == (byte)'E' && arg.getByte(offset + 1) == (byte)'S') {
      if (readableBytes == TcpHeader.MARKER_BYTES_SIZE + TcpHeader.MESSAGE_LENGTH_SIZE) {
        final int length=arg.getInt(offset + MESSAGE_LENGTH_OFFSET);
        if (length == TcpTransport.PING_DATA_SIZE) {
          sb.append(" [ping]").append(' ').append(eventName).append(": ").append(readableBytes).append('B');
          return sb.toString();
        }
      }
 else       if (readableBytes >= TcpHeader.HEADER_SIZE) {
        final int length=arg.getInt(offset + MESSAGE_LENGTH_OFFSET);
        final long requestId=arg.getLong(offset + REQUEST_ID_OFFSET);
        final byte status=arg.getByte(offset + STATUS_OFFSET);
        final boolean isRequest=TransportStatus.isRequest(status);
        final String type=isRequest ? "request" : "response";
        final String version=Version.fromId(arg.getInt(offset + VERSION_ID_OFFSET)).toString();
        sb.append(" [length: ").append(length);
        sb.append(", request id: ").append(requestId);
        sb.append(", type: ").append(type);
        sb.append(", version: ").append(version);
        if (isRequest) {
          final int remaining=readableBytes - ACTION_OFFSET;
          final ByteBuf slice=arg.slice(offset + ACTION_OFFSET,remaining);
          try (StreamInput in=in(status,slice,remaining)){
            try (ThreadContext context=new ThreadContext(Settings.EMPTY)){
              context.readHeaders(in);
            }
             sb.append(", action: ").append(in.readString());
          }
         }
        sb.append(']');
        sb.append(' ').append(eventName).append(": ").append(readableBytes).append('B');
        return sb.toString();
      }
    }
  }
  return super.format(ctx,eventName,arg);
}
