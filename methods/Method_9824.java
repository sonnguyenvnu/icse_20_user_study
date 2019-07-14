/** 
 * Shows about.
 * @param context the specified context
 */
@RequestProcessing(value="/about",method=HttpMethod.GET) @Before(StopwatchStartAdvice.class) @After(StopwatchEndAdvice.class) public void showAbout(final RequestContext context){
  context.sendRedirect(Latkes.getServePath() + "/member/admin");
}
