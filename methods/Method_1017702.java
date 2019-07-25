/** 
 * @param random a source of randomness used when locating othergenerators
 * @return an access point for the available generators
 */
protected Generators gen(SourceOfRandomness random){
  return repo.withRandom(random);
}
