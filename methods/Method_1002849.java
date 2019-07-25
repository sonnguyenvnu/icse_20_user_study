private String decorate(String msg){
  final String prefix=ctx.toString();
  return new StringBuilder(prefix.length() + 1 + msg.length()).append(prefix).append(' ').append(msg).toString();
}
