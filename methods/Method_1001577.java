public boolean contains(String member){
  for (  Member att : members) {
    if (att.getDisplay(false).startsWith(member)) {
      return true;
    }
  }
  return false;
}
