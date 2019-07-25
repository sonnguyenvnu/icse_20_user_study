private static byte gethex(byte b){
  if (b <= 57) {
    return (byte)(b - 48);
  }
  if (b >= 97) {
    return (byte)(b - 97 + 10);
  }
  return (byte)(b - 65 + 10);
}
