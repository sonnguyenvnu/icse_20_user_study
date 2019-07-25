private String repeat(int times,char character){
  char[] characters=new char[times];
  Arrays.fill(characters,character);
  return new String(characters);
}
