@Override public void beforeChildren(TagNode node,SpannableStringBuilder builder){
  TodoItems todoItem=null;
  if (node.getChildTags() != null && node.getChildTags().length > 0) {
    for (    TagNode tagNode : node.getChildTags()) {
      Logger.e(tagNode.getName(),tagNode.getText());
      if (tagNode.getName() != null && tagNode.getName().equals("input")) {
        todoItem=new TodoItems();
        todoItem.isChecked=tagNode.getAttributeByName("checked") != null;
        break;
      }
    }
  }
  if ("ol".equals(getParentName(node))) {
    builder.append(String.valueOf(getMyIndex(node))).append(". ");
  }
 else   if ("ul".equals(getParentName(node))) {
    if (todoItem != null) {
      if (checked == null || unchecked == null) {
        builder.append(todoItem.isChecked ? "?" : "?");
      }
 else {
        builder.append(SpannableBuilder.builder().append(todoItem.isChecked ? checked : unchecked)).append(" ");
      }
    }
 else {
      builder.append("\u2022 ");
    }
  }
}
