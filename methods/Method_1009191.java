public static long length(@NotNull DataInputStream dataInputStream) throws IOException {
  return readUnsignedInt(dataInputStream);
}
