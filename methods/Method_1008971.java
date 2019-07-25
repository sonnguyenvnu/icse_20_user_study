public static void recurse(Alterations alterations,RelationshipsPart thisRP,RelationshipsPart otherRP) throws Docx4JException {
  log.info("######### @" + thisRP.getPartName().getName() + "#########");
  log.info("uniques -------");
  List<Relationship> uniques=thisRP.uniqueToThis(otherRP);
  addPartsForRels(alterations.getPartsAdded(),uniques,thisRP);
  List<Relationship> missings=thisRP.uniqueToOther(otherRP);
  addPartsForRels(alterations.getPartsDeleted(),missings,otherRP);
  if (!thisRP.isContentEqual(otherRP)) {
    alterations.getPartsModified().add(new Alteration(thisRP,toStorageFormat(thisRP)));
  }
  log.info("content -------");
  List<Relationship> altered=thisRP.differingContent(otherRP);
  addPartsForRels(alterations.getPartsModified(),altered,thisRP);
  log.info("recurse ------- ");
  for (  Relationship r : thisRP.getJaxbElement().getRelationship()) {
    if (r.getTargetMode() != null && r.getTargetMode().equals("External")) {
    }
 else {
      if (uniques.contains(r)) {
        addTree(alterations.getPartsAdded(),thisRP.getPart(r).getRelationshipsPart());
      }
 else       if (missings.contains(r)) {
        addTree(alterations.getPartsDeleted(),thisRP.getPart(r).getRelationshipsPart());
      }
 else {
        Part thisPart=thisRP.getPart(r);
        Part otherPart=otherRP.getPart(RelationshipsPart.getRelationshipByTarget(otherRP,r.getTarget()));
        if (thisPart.getRelationshipsPart() == null) {
          if (otherPart.getRelationshipsPart() != null) {
            alterations.getPartsDeleted().add(new Alteration(thisPart.getPartName(),toStorageFormat(thisPart.getRelationshipsPart())));
            addTree(alterations.getPartsDeleted(),thisPart.getRelationshipsPart());
          }
        }
 else {
          if (otherPart.getRelationshipsPart() == null) {
            alterations.getPartsAdded().add(new Alteration(thisPart.getPartName(),toStorageFormat(thisPart.getRelationshipsPart())));
            addTree(alterations.getPartsAdded(),thisPart.getRelationshipsPart());
          }
 else {
            recurse(alterations,thisPart.getRelationshipsPart(),otherPart.getRelationshipsPart());
          }
        }
      }
    }
  }
}
