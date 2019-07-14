/** 
 * @return true if component has common dynamic props, false - otherwise. If so {@link #getCommonDynamicProps()} will return not null value
 * @see DynamicPropsManager
 */
boolean hasCommonDynamicProps(){
  return mCommonDynamicProps != null;
}
