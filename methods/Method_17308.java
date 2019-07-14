/** 
 * @return a copy strategy that performs an identity function, for use by store-by-reference 
 */
static Copier identity(){
  return IdentityCopier.INSTANCE;
}
