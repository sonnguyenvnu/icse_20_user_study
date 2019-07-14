/** 
 * ( begin auto-generated from noiseSeed.xml ) Sets the seed value for <b>noise()</b>. By default, <b>noise()</b> produces different results each time the program is run. Set the <b>value</b> parameter to a constant to return the same pseudo-random numbers each time the software is run. ( end auto-generated )
 * @webref math:random
 * @param seed seed value
 * @see PApplet#noise(float,float,float)
 * @see PApplet#noiseDetail(int,float)
 * @see PApplet#random(float,float)
 * @see PApplet#randomSeed(long)
 */
public void noiseSeed(long seed){
  if (perlinRandom == null)   perlinRandom=new Random();
  perlinRandom.setSeed(seed);
  perlin=null;
}
