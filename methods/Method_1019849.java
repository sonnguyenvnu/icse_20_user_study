/** 
 * @see Sequencer#claim(long)
 */
@Override public void claim(long sequence){
  cursor.set(sequence);
}
