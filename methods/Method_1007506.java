/** 
 * Sets up a listener on the supplied port, and when a fresh connection comes in, it creates a new instance of the httpSessionClass task and exceutes it on the supplied scheduler.   It is the httpSession task's responsbility to close the socket. 
 * @param port. Port to listen for http connections.
 * @param httpSessionClass      class of task to instantiation on incoming connection
 * @param httpSessionScheduler  the scheduler on which to schedule the http session task.
 * @throws IOException
 */
public void listen(int port,Class<? extends HttpSession> httpSessionClass,Scheduler httpSessionScheduler) throws IOException {
  nio.listen(port,httpSessionClass,httpSessionScheduler);
}
