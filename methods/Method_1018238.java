/** 
 * Write a character or its code to the console.
 * @param integer The charcode to be written to the log.
 * @param width The width of the charcode in bits.
 */
static void logchar(int integer,int width){
  if (integer > ' ' && integer <= '}') {
    log("'" + (char)integer + "':" + width + " ");
  }
 else {
    log(integer,width);
  }
}
