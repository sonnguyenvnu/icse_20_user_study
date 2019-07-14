/** 
 * Adds an encoding character to the encoded key value string - Exact/Approx version
 * @param mainExact primary encoding character to be added to encoded key string if m_encodeExact is set
 * @param main primary encoding character to be added to encoded key string
 */
void MetaphAddExactApprox(String mainExact,String main){
  if (m_encodeExact) {
    MetaphAdd(mainExact);
  }
 else {
    MetaphAdd(main);
  }
}
