/** 
 * Build a new cli application.
 */
public static Application create(ApplicationContext ctx){
  return ctx.getBean(ApplicationImpl.class);
}
