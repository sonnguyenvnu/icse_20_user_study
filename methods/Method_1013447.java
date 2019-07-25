public final ValueEnumeration elements(){
  if (this.argLen == 1) {
    if (!(this.domains[0] instanceof Enumerable)) {
      Assert.fail("The domains of formal parameters must be enumerable.");
    }
    return ((Enumerable)this.domains[0]).elements();
  }
  return new Enumerator();
}
