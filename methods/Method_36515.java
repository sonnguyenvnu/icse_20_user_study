@Override public String dump(){
  StringBuilder sb=new StringBuilder(super.dump());
  Collection<Binding> bindings=service.getBindings();
  for (  Binding binding : bindings) {
    sb.append("\n|------>[binding]-").append(binding.dump());
  }
  return sb.toString();
}
