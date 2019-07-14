private static String getDebugInfo(SectionTree tree){
synchronized (tree) {
    if (tree.isReleased()) {
      return "[Released Tree]";
    }
    final StringBuilder sb=new StringBuilder();
    sb.append("tag: ");
    sb.append(tree.mTag);
    sb.append(", currentSection.size: ");
    sb.append(tree.mCurrentSection != null ? tree.mCurrentSection.getCount() : null);
    sb.append(", currentSection.name: ");
    sb.append(tree.mCurrentSection != null ? tree.mCurrentSection.getSimpleName() : null);
    sb.append(", nextSection.size: ");
    sb.append(tree.mNextSection != null ? tree.mNextSection.getCount() : null);
    sb.append(", nextSection.name: ");
    sb.append(tree.mNextSection != null ? tree.mNextSection.getSimpleName() : null);
    sb.append(", pendingChangeSets.size: ");
    sb.append(tree.mPendingChangeSets.size());
    sb.append(", pendingStateUpdates.size: ");
    sb.append(tree.mPendingStateUpdates.mAllStateUpdates.size());
    sb.append(", pendingNonLazyStateUpdates.size: ");
    sb.append(tree.mPendingStateUpdates.mNonLazyStateUpdates.size());
    sb.append("\n");
    return sb.toString();
  }
}
