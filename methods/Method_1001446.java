/** 
 * Format a number in accordance of the format string given at the initialisation of this instance.
 * @param buffer Buffer to which the formated output will be appended.
 * @param number Number to be formated.
 */
public void form(StringBuffer buffer,long number){
  if (_descriptorType == FLOAT_DESCRIPTOR) {
    form(buffer,(double)number);
  }
 else {
    buffer.append(form(number < 0,Long.toString(Math.abs(number),_descriptor == 'o' ? 8 : (_descriptor == 'x' ? 16 : 10)),""));
  }
}
