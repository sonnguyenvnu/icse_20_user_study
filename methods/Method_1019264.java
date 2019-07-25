public void display(PrintWriter writer){
  writer.println(getMessage());
  if (usage != null) {
    writer.println();
    displayUsage(writer);
  }
}
