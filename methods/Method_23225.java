/** 
 * ( begin auto-generated from randomGaussian.xml ) Returns a float from a random series of numbers having a mean of 0 and standard deviation of 1. Each time the <b>randomGaussian()</b> function is called, it returns a number fitting a Gaussian, or normal, distribution. There is theoretically no minimum or maximum value that <b>randomGaussian()</b> might return. Rather, there is just a very low probability that values far from the mean will be returned; and a higher probability that numbers near the mean will be returned. ( end auto-generated )
 * @webref math:random
 * @see PApplet#random(float,float)
 * @see PApplet#noise(float,float,float)
 */
public final float randomGaussian(){
  if (internalRandom == null) {
    internalRandom=new Random();
  }
  return (float)internalRandom.nextGaussian();
}
