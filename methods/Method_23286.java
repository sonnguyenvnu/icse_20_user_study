/** 
 * @param num the number to format
 */
static public String nfc(int num){
  if ((int_nf != null) && (int_nf_digits == 0) && int_nf_commas) {
    return int_nf.format(num);
  }
  int_nf=NumberFormat.getInstance();
  int_nf.setGroupingUsed(true);
  int_nf_commas=true;
  int_nf.setMinimumIntegerDigits(0);
  int_nf_digits=0;
  return int_nf.format(num);
}
