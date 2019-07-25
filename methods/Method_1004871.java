public void diff(List<String> diffs,Table other,String nameA,String nameB){
  if (!this.getCharset().equals(other.getCharset())) {
    diffs.add(this.fullName() + " differs in charset: " + nameA + " is " + this.getCharset() + " but " + nameB + " is " + other.getCharset());
  }
  if (!this.getPKString().equals(other.getPKString())) {
    diffs.add(this.fullName() + " differs in PKs: " + nameA + " is " + this.getPKString() + " but " + nameB + " is " + other.getPKString());
  }
  if (!this.getName().equals(other.getName())) {
    diffs.add(this.fullName() + " differs in name: " + nameA + " is " + this.getName() + " but " + nameB + " is " + other.getName());
  }
  diffColumnList(diffs,this,other,nameA,nameB);
  diffColumnList(diffs,other,this,nameB,nameA);
}
