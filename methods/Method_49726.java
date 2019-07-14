/** 
 * Returns short  {@link OpenLocationCode} from the full Open Location Code created by removingfour or six digits, depending on the provided reference point. It removes as many digits as possible.
 * @param referenceLatitude Degrees.
 * @param referenceLongitude Degrees.
 * @return A short code if possible.
 */
public OpenLocationCode shorten(double referenceLatitude,double referenceLongitude){
  if (!isFull()) {
    throw new IllegalStateException("shorten() method could only be called on a full code.");
  }
  if (isPadded()) {
    throw new IllegalStateException("shorten() method can not be called on a padded code.");
  }
  CodeArea codeArea=decode();
  double range=Math.max(Math.abs(referenceLatitude - codeArea.getCenterLatitude()),Math.abs(referenceLongitude - codeArea.getCenterLongitude()));
  for (int i=4; i >= 1; i--) {
    if (range < (computeLatitudePrecision(i * 2) * 0.3)) {
      return new OpenLocationCode(code.substring(i * 2));
    }
  }
  throw new IllegalArgumentException("Reference location is too far from the Open Location Code center.");
}
