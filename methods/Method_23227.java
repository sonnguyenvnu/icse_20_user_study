/** 
 * @see #noiseDetail(int)
 * @param falloff falloff factor for each octave
 */
public void noiseDetail(int lod,float falloff){
  if (lod > 0)   perlin_octaves=lod;
  if (falloff > 0)   perlin_amp_falloff=falloff;
}
