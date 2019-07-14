public void checkSection(){
  if (scrollingByUser && fastScroll != null || sectionsType != 0 && sectionsAdapter != null) {
    LayoutManager layoutManager=getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      LinearLayoutManager linearLayoutManager=(LinearLayoutManager)layoutManager;
      if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
        if (sectionsAdapter != null) {
          if (sectionsType == 1) {
            int firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
            int lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
            int visibleItemCount=Math.abs(lastVisibleItem - firstVisibleItem) + 1;
            if (firstVisibleItem == NO_POSITION) {
              return;
            }
            if (scrollingByUser && fastScroll != null) {
              Adapter adapter=getAdapter();
              if (adapter instanceof FastScrollAdapter) {
                fastScroll.setProgress(Math.min(1.0f,firstVisibleItem / (float)(adapter.getItemCount() - visibleItemCount + 1)));
              }
            }
            headersCache.addAll(headers);
            headers.clear();
            if (sectionsAdapter.getItemCount() == 0) {
              return;
            }
            if (currentFirst != firstVisibleItem || currentVisible != visibleItemCount) {
              currentFirst=firstVisibleItem;
              currentVisible=visibleItemCount;
              sectionsCount=1;
              startSection=sectionsAdapter.getSectionForPosition(firstVisibleItem);
              int itemNum=firstVisibleItem + sectionsAdapter.getCountForSection(startSection) - sectionsAdapter.getPositionInSectionForPosition(firstVisibleItem);
              while (itemNum < firstVisibleItem + visibleItemCount) {
                itemNum+=sectionsAdapter.getCountForSection(startSection + sectionsCount);
                sectionsCount++;
              }
            }
            int itemNum=firstVisibleItem;
            for (int a=startSection; a < startSection + sectionsCount; a++) {
              View header=null;
              if (!headersCache.isEmpty()) {
                header=headersCache.get(0);
                headersCache.remove(0);
              }
              header=getSectionHeaderView(a,header);
              headers.add(header);
              int count=sectionsAdapter.getCountForSection(a);
              if (a == startSection) {
                int pos=sectionsAdapter.getPositionInSectionForPosition(itemNum);
                if (pos == count - 1) {
                  header.setTag(-header.getHeight());
                }
 else                 if (pos == count - 2) {
                  View child=getChildAt(itemNum - firstVisibleItem);
                  int headerTop;
                  if (child != null) {
                    headerTop=child.getTop();
                  }
 else {
                    headerTop=-AndroidUtilities.dp(100);
                  }
                  if (headerTop < 0) {
                    header.setTag(headerTop);
                  }
 else {
                    header.setTag(0);
                  }
                }
 else {
                  header.setTag(0);
                }
                itemNum+=count - sectionsAdapter.getPositionInSectionForPosition(firstVisibleItem);
              }
 else {
                View child=getChildAt(itemNum - firstVisibleItem);
                if (child != null) {
                  header.setTag(child.getTop());
                }
 else {
                  header.setTag(-AndroidUtilities.dp(100));
                }
                itemNum+=count;
              }
            }
          }
 else           if (sectionsType == 2) {
            pinnedHeaderShadowTargetAlpha=0.0f;
            if (sectionsAdapter.getItemCount() == 0) {
              return;
            }
            int childCount=getChildCount();
            int maxBottom=0;
            int minBottom=Integer.MAX_VALUE;
            View minChild=null;
            int minBottomSection=Integer.MAX_VALUE;
            View minChildSection=null;
            for (int a=0; a < childCount; a++) {
              View child=getChildAt(a);
              int bottom=child.getBottom();
              if (bottom <= sectionOffset + getPaddingTop()) {
                continue;
              }
              if (bottom < minBottom) {
                minBottom=bottom;
                minChild=child;
              }
              maxBottom=Math.max(maxBottom,bottom);
              if (bottom < sectionOffset + getPaddingTop() + AndroidUtilities.dp(32)) {
                continue;
              }
              if (bottom < minBottomSection) {
                minBottomSection=bottom;
                minChildSection=child;
              }
            }
            if (minChild == null) {
              return;
            }
            ViewHolder holder=getChildViewHolder(minChild);
            if (holder == null) {
              return;
            }
            int firstVisibleItem=holder.getAdapterPosition();
            int startSection=sectionsAdapter.getSectionForPosition(firstVisibleItem);
            if (startSection < 0) {
              return;
            }
            if (currentFirst != startSection || pinnedHeader == null) {
              pinnedHeader=getSectionHeaderView(startSection,pinnedHeader);
              currentFirst=startSection;
            }
            if (pinnedHeader != null && minChildSection != null && minChildSection.getClass() != pinnedHeader.getClass()) {
              pinnedHeaderShadowTargetAlpha=1.0f;
            }
            int count=sectionsAdapter.getCountForSection(startSection);
            int pos=sectionsAdapter.getPositionInSectionForPosition(firstVisibleItem);
            int paddingTop=getPaddingTop();
            int sectionOffsetY=maxBottom != 0 && maxBottom < (getMeasuredHeight() - getPaddingBottom()) ? 0 : sectionOffset;
            if (pos == count - 1) {
              int headerHeight=pinnedHeader.getHeight();
              int headerTop=paddingTop;
              if (minChild != null) {
                int available=minChild.getTop() - paddingTop - sectionOffset + minChild.getHeight();
                if (available < headerHeight) {
                  headerTop=available - headerHeight;
                }
              }
 else {
                headerTop=-AndroidUtilities.dp(100);
              }
              if (headerTop < 0) {
                pinnedHeader.setTag(paddingTop + sectionOffsetY + headerTop);
              }
 else {
                pinnedHeader.setTag(paddingTop + sectionOffsetY);
              }
            }
 else {
              pinnedHeader.setTag(paddingTop + sectionOffsetY);
            }
            invalidate();
          }
        }
 else {
          int firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
          int lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
          int visibleItemCount=Math.abs(lastVisibleItem - firstVisibleItem) + 1;
          if (firstVisibleItem == NO_POSITION) {
            return;
          }
          if (scrollingByUser && fastScroll != null) {
            Adapter adapter=getAdapter();
            if (adapter instanceof FastScrollAdapter) {
              fastScroll.setProgress(Math.min(1.0f,firstVisibleItem / (float)(adapter.getItemCount() - visibleItemCount + 1)));
            }
          }
        }
      }
    }
  }
}
