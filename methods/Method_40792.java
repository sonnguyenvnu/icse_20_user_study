public String toString(){
  if (args.positional.size() != 0) {
    return "(" + op + " " + args + ")";
  }
 else {
    return "(" + op + ")";
  }
}
