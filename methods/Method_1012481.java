String dump(){
  dumped=true;
  if (rules.length() > 0 && !rules.endsWith("\n"))   rules+="\n";
  return "! " + escape(name) + " " + flags + " " + escape(arrayToList(inputs)) + " " + escape(arrayToList(outputs)) + " " + escape(infoText) + " " + escape(rules);
}
