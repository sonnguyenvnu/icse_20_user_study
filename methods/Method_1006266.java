/** 
 * Creates an Optional<DOI> from various schemes including URL, URN, and plain DOIs. Useful for suppressing the <c>IllegalArgumentException</c> of the Constructor and checking for Optional.isPresent() instead.
 * @param doi the DOI string
 * @return an Optional containing the DOI or an empty Optional
 */
public static Optional<DOI> parse(String doi){
  try {
    String cleanedDOI=doi.trim();
    cleanedDOI=doi.replaceAll(" ","");
    return Optional.of(new DOI(cleanedDOI));
  }
 catch (  IllegalArgumentException|NullPointerException e) {
    return Optional.empty();
  }
}
