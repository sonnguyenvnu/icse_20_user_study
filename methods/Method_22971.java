protected DefaultMutableTreeNode buildContribTree(){
  DefaultMutableTreeNode contribExamplesNode=new DefaultMutableTreeNode(Language.text("examples.contributed"));
  try {
    File[] subfolders=ContributionType.EXAMPLES.listCandidates(examplesContribFolder);
    if (subfolders != null) {
      for (      File sub : subfolders) {
        StringDict props=Contribution.loadProperties(sub,ContributionType.EXAMPLES);
        if (props != null) {
          if (ExamplesContribution.isCompatible(base,props)) {
            DefaultMutableTreeNode subNode=new DefaultMutableTreeNode(props.get("name"));
            if (base.addSketches(subNode,sub,true)) {
              contribExamplesNode.add(subNode);
              int exampleNodeNumber=-1;
              for (int i=0; i < subNode.getChildCount(); i++) {
                if (subNode.getChildAt(i).toString().equals("examples")) {
                  exampleNodeNumber=i;
                }
              }
              if (exampleNodeNumber != -1) {
                TreeNode exampleNode=subNode.getChildAt(exampleNodeNumber);
                subNode.remove(exampleNodeNumber);
                int count=exampleNode.getChildCount();
                for (int j=0; j < count; j++) {
                  subNode.add((DefaultMutableTreeNode)exampleNode.getChildAt(0));
                }
              }
            }
          }
        }
      }
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return contribExamplesNode;
}
