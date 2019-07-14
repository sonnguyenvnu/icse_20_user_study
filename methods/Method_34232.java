@org.codehaus.jackson.annotate.JsonIgnore @com.fasterxml.jackson.annotation.JsonIgnore public boolean isScoped(){
  return this.scope != null && !this.scope.isEmpty();
}
