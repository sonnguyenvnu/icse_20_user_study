/** 
 * Apply a transducer to an input sequence to produce the k highest-scoring output sequences.
 * @param model the <code>Transducer</code>
 * @param input the input sequence
 * @param k the number of answers to return
 * @return array of the k highest-scoring output sequences
 */
public static Sequence[] apply(Transducer model,Sequence input,int k){
  Sequence[] answers;
  if (k == 1) {
    answers=new Sequence[1];
    answers[0]=model.transduce(input);
  }
 else {
    MaxLatticeDefault lattice=new MaxLatticeDefault(model,input,null,cacheSizeOption.value());
    answers=lattice.bestOutputSequences(k).toArray(new Sequence[0]);
  }
  return answers;
}
