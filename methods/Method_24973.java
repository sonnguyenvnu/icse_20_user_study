static public String getServerCode(int listenPort,boolean hasInts,boolean hasFloats){
  String serverCode="" + "class TweakModeServer extends Thread\n" + "{\n" + "  protected DatagramSocket socket = null;\n" + "  protected boolean running = true;\n" + "  final int INT_VAR = 0;\n" + "  final int FLOAT_VAR = 1;\n" + "  final int SHUTDOWN = 0xffffffff;\n" + "  public TweakModeServer() {\n" + "    this(\"TweakModeServer\");\n" + "  }\n" + "  public TweakModeServer(String name) {\n" + "    super(name);\n" + "  }\n" + "  public void setup()\n" + "  {\n" + "    try {\n" + "      socket = new DatagramSocket(" + listenPort + ");\n" + "      socket.setSoTimeout(250);\n" + "    } catch (IOException e) {\n" + "      println(\"error: could not create TweakMode server socket\");\n" + "    }\n" + "  }\n" + "  public void run()\n" + "  {\n" + "    byte[] buf = new byte[256];\n" + "    while(running)\n" + "    {\n" + "      try {\n" + "        DatagramPacket packet = new DatagramPacket(buf, buf.length);\n" + "        socket.receive(packet);\n" + "        ByteBuffer bb = ByteBuffer.wrap(buf);\n" + "        int type = bb.getInt(0);\n" + "        int index = bb.getInt(4);\n";
  if (hasInts) {
    serverCode+="        if (type == INT_VAR) {\n" + "          int val = bb.getInt(8);\n" + "          tweakmode_int[index] = val;\n" + "        }\n" + "        else ";
  }
  if (hasFloats) {
    serverCode+="         if (type == FLOAT_VAR) {\n" + "          float val = bb.getFloat(8);\n" + "          tweakmode_float[index] = val;\n" + "        }\n" + "        else";
  }
  serverCode+="        if (type == SHUTDOWN) {\n" + "          running = false;\n" + "        }\n" + "      } catch (SocketTimeoutException e) {\n" + "        // nothing to do here just try receiving again\n" + "      } catch (Exception e) {\n" + "      }\n" + "    }\n" + "    socket.close();\n" + "  }\n" + "}\n\n\n";
  return serverCode;
}
