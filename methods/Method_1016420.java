/** 
 * age in milliseconds (difference between now and last_modified)
 * @return age in milliseconds
 */
public long age(){
  final Date lm=lastModified();
  final Date now=new Date();
  return now.getTime() - lm.getTime();
}
