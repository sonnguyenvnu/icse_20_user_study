/** 
 * Converts the given sequence into another sequence according to this transducer. For exmaple, probabilistic transducer may do something like Viterbi here. Subclasses of transducer may specify that they only accept special kinds of sequence.
 * @param input Input sequence
 * @return Sequence output by this transudcer 
 */
public Sequence transduce(Sequence input){
  return maxLatticeFactory.newMaxLattice(this,(Sequence)input).bestOutputSequence();
}
