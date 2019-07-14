/** 
 * Adds an encoding character to the encoded key value string - Exact/Approx version
 * @param mainExact primary encoding character to be added to encoded key string if m_encodeExact is set
 * @param altExact alternative encoding character to be added to encoded alternative key string if m_encodeExact is set
 * @param main primary encoding character to be added to encoded key string
 * @param alt alternative encoding character to be added to encoded alternative key string
 */
void MetaphAddExactApprox(String mainExact,String altExact,String main,String alt){
  if (m_encodeExact) {
    MetaphAdd(mainExact,altExact);
  }
 else {
    MetaphAdd(main,alt);
  }
}
