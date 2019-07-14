public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx){
  packageName=concatIdentifiers(ctx.qualifiedName().Identifier());
}
