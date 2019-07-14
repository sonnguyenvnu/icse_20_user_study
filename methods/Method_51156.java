private void renderPackage(PackageNode pnode){
  String str;
  if (pnode.getParent() == null) {
    str="Aggregate";
  }
 else {
    str=pnode.getPackageName();
    pnode.getParent().addNumberOfViolation(pnode.getNumberOfViolations());
  }
  packageBuf.insert(length,"<tr><td><b>" + str + "</b></td>" + " <td>-</td>" + " <td>" + pnode.getNumberOfViolations() + "</td>" + "</tr>" + PMD.EOL);
}
