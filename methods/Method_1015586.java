@ManagedOperation(description="Prints the last N conditions that triggered a rule action") public String executions(){
  StringBuilder sb=new StringBuilder();
  for (  String execution : executions)   sb.append(execution + "\n");
  return sb.toString();
}
