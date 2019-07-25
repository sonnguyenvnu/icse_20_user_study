public void process(InjectorImpl injector,List<Element> elements){
  Errors errorsAnyElement=this.errors;
  this.injector=injector;
  try {
    for (Iterator<Element> i=elements.iterator(); i.hasNext(); ) {
      Element element=i.next();
      this.errors=errorsAnyElement.withSource(element.getSource());
      Boolean allDone=element.acceptVisitor(this);
      if (allDone) {
        i.remove();
      }
    }
  }
  finally {
    this.errors=errorsAnyElement;
    this.injector=null;
  }
}
