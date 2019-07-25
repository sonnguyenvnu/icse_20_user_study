protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  final AsyncContext async=req.startAsync();
  async.setTimeout(30000);
  new kilim.Task(){
    public void execute() throws Pausable, Exception {
      if (delay > 0)       Task.sleep(delay);
      reply(async);
    }
  }
.start();
}
