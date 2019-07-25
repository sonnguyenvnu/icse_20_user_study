/** 
 * Add a  {@link Sequence} into this aggregate.  This should only be used duringinitialisation.  Use  {@link SequenceGroup#addWhileRunning(Cursored,Sequence)}
 * @param sequence to be added to the aggregate.
 * @see SequenceGroup#addWhileRunning(Cursored,Sequence)
 */
public void add(final Sequence sequence){
  Sequence[] oldSequences;
  Sequence[] newSequences;
  do {
    oldSequences=sequences;
    final int oldSize=oldSequences.length;
    newSequences=new Sequence[oldSize + 1];
    System.arraycopy(oldSequences,0,newSequences,0,oldSize);
    newSequences[oldSize]=sequence;
  }
 while (!SEQUENCE_UPDATER.compareAndSet(this,oldSequences,newSequences));
}
