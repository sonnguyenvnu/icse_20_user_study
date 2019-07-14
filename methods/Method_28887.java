private static void sendCommand(final RedisOutputStream os,final byte[] command,final byte[]... args){
  try {
    os.write(ASTERISK_BYTE);
    os.writeIntCrLf(args.length + 1);
    os.write(DOLLAR_BYTE);
    os.writeIntCrLf(command.length);
    os.write(command);
    os.writeCrLf();
    for (    final byte[] arg : args) {
      os.write(DOLLAR_BYTE);
      os.writeIntCrLf(arg.length);
      os.write(arg);
      os.writeCrLf();
    }
  }
 catch (  IOException e) {
    throw new JedisConnectionException(e);
  }
}
