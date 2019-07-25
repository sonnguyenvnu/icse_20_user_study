/** 
 * Put a PhysicalFont into FontMappings,  by case-insensitive name.  (Although Word always uses Title Case for font names, it is actually case insensitive; the spec is silent on this.)  
 * @param key
 * @param pf
 */
public void put(String key,PhysicalFont pf){
  PhysicalFont priorPf=fontMappings.get(key.toLowerCase());
  if (priorPf != null) {
    if (priorPf == pf) {
      return;
    }
    log.warn("Overwriting existing fontMapping: " + key.toLowerCase());
  }
  fontMappings.put(key.toLowerCase(),pf);
}
