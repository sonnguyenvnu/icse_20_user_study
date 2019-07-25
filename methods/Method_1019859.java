/** 
 * @see Sequencer#claim(long)
 */
@Override public void claim(long sequence){
  this.nextValue=sequence;
}
