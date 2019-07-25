@CliCommand(value="tasks list",help="List tasks") public String list(){
  return tasks.toString();
}
