/** 
 * Reset all of the equalizer amplification values (including pre-amplification) to zero.
 */
public final void reset(){
  preamp=0f;
  for (int i=0; i < bandCount; i++) {
    bandAmps[i]=0f;
  }
  fireEqualizerChanged();
}
