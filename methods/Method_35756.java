private static char randomChar(){
  final Random random=new Random();
  final int index=random.nextInt(CHARS.length());
  return CHARS.charAt(index);
}
