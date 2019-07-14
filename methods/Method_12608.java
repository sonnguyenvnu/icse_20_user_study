/** 
 * ??????????????????????
 * @param input
 * @return char
 */
static char regularize(char input,boolean lowercase){
  if (input == 12288) {
    input=(char)32;
  }
 else   if (input > 65280 && input < 65375) {
    input=(char)(input - 65248);
  }
 else   if (input >= 'A' && input <= 'Z' && lowercase) {
    input+=32;
  }
  return input;
}
