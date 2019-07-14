@Override public boolean needPresentFragment(BaseFragment fragment,boolean removeLast,boolean forceWithoutAnimation,ActionBarLayout layout){
  if (ArticleViewer.hasInstance() && ArticleViewer.getInstance().isVisible()) {
    ArticleViewer.getInstance().close(false,true);
  }
  if (AndroidUtilities.isTablet()) {
    drawerLayoutContainer.setAllowOpenDrawer(!(fragment instanceof LoginActivity || fragment instanceof CountrySelectActivity) && layersActionBarLayout.getVisibility() != View.VISIBLE,true);
    if (fragment instanceof DialogsActivity) {
      DialogsActivity dialogsActivity=(DialogsActivity)fragment;
      if (dialogsActivity.isMainDialogList() && layout != actionBarLayout) {
        actionBarLayout.removeAllFragments();
        actionBarLayout.presentFragment(fragment,removeLast,forceWithoutAnimation,false,false);
        layersActionBarLayout.removeAllFragments();
        layersActionBarLayout.setVisibility(View.GONE);
        drawerLayoutContainer.setAllowOpenDrawer(true,false);
        if (!tabletFullSize) {
          shadowTabletSide.setVisibility(View.VISIBLE);
          if (rightActionBarLayout.fragmentsStack.isEmpty()) {
            backgroundTablet.setVisibility(View.VISIBLE);
          }
        }
        return false;
      }
    }
    if (fragment instanceof ChatActivity) {
      if (!tabletFullSize && layout == rightActionBarLayout || tabletFullSize && layout == actionBarLayout) {
        boolean result=!(tabletFullSize && layout == actionBarLayout && actionBarLayout.fragmentsStack.size() == 1);
        if (!layersActionBarLayout.fragmentsStack.isEmpty()) {
          for (int a=0; a < layersActionBarLayout.fragmentsStack.size() - 1; a++) {
            layersActionBarLayout.removeFragmentFromStack(layersActionBarLayout.fragmentsStack.get(0));
            a--;
          }
          layersActionBarLayout.closeLastFragment(!forceWithoutAnimation);
        }
        if (!result) {
          actionBarLayout.presentFragment(fragment,false,forceWithoutAnimation,false,false);
        }
        return result;
      }
 else       if (!tabletFullSize && layout != rightActionBarLayout) {
        rightActionBarLayout.setVisibility(View.VISIBLE);
        backgroundTablet.setVisibility(View.GONE);
        rightActionBarLayout.removeAllFragments();
        rightActionBarLayout.presentFragment(fragment,removeLast,true,false,false);
        if (!layersActionBarLayout.fragmentsStack.isEmpty()) {
          for (int a=0; a < layersActionBarLayout.fragmentsStack.size() - 1; a++) {
            layersActionBarLayout.removeFragmentFromStack(layersActionBarLayout.fragmentsStack.get(0));
            a--;
          }
          layersActionBarLayout.closeLastFragment(!forceWithoutAnimation);
        }
        return false;
      }
 else       if (tabletFullSize && layout != actionBarLayout) {
        actionBarLayout.presentFragment(fragment,actionBarLayout.fragmentsStack.size() > 1,forceWithoutAnimation,false,false);
        if (!layersActionBarLayout.fragmentsStack.isEmpty()) {
          for (int a=0; a < layersActionBarLayout.fragmentsStack.size() - 1; a++) {
            layersActionBarLayout.removeFragmentFromStack(layersActionBarLayout.fragmentsStack.get(0));
            a--;
          }
          layersActionBarLayout.closeLastFragment(!forceWithoutAnimation);
        }
        return false;
      }
 else {
        if (!layersActionBarLayout.fragmentsStack.isEmpty()) {
          for (int a=0; a < layersActionBarLayout.fragmentsStack.size() - 1; a++) {
            layersActionBarLayout.removeFragmentFromStack(layersActionBarLayout.fragmentsStack.get(0));
            a--;
          }
          layersActionBarLayout.closeLastFragment(!forceWithoutAnimation);
        }
        actionBarLayout.presentFragment(fragment,actionBarLayout.fragmentsStack.size() > 1,forceWithoutAnimation,false,false);
        return false;
      }
    }
 else     if (layout != layersActionBarLayout) {
      layersActionBarLayout.setVisibility(View.VISIBLE);
      drawerLayoutContainer.setAllowOpenDrawer(false,true);
      if (fragment instanceof LoginActivity) {
        backgroundTablet.setVisibility(View.VISIBLE);
        shadowTabletSide.setVisibility(View.GONE);
        shadowTablet.setBackgroundColor(0x00000000);
      }
 else {
        shadowTablet.setBackgroundColor(0x7f000000);
      }
      layersActionBarLayout.presentFragment(fragment,removeLast,forceWithoutAnimation,false,false);
      return false;
    }
    return true;
  }
 else {
    boolean allow=true;
    if (fragment instanceof LoginActivity) {
      if (mainFragmentsStack.size() == 0) {
        allow=false;
      }
    }
 else     if (fragment instanceof CountrySelectActivity) {
      if (mainFragmentsStack.size() == 1) {
        allow=false;
      }
    }
    drawerLayoutContainer.setAllowOpenDrawer(allow,false);
    return true;
  }
}
