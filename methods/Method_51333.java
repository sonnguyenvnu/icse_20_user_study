@Override public String toString(){
  return "[PropertyDescriptor: name=" + name() + ',' + " type=" + (isMultiValue() ? "List<" + type() + '>' : type()) + ',' + " value=" + defaultValue() + ']';
}
