/** 
 * advice
 */
@Before("matchLongArg() && args(productId)") public void before(Long productId){
  System.out.println("###before,get args:" + productId);
}
