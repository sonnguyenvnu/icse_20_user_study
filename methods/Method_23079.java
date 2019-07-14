void writeBoolean(boolean value){
  emitIndent();
  writer.println("<" + (value ? "true" : "false") + "/>");
}
