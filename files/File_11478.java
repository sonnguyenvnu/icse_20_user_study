package com.flyco.tablayoutsamples.utils;

import android.util.SparseArray;
import android.view.View;

@SuppressWarnings({ "unchecked" })
public class ViewFindUtils
{
	/**
	 * ViewHolderç®€æ´?å†™æ³•,é?¿å…?é€‚é…?å™¨ä¸­é‡?å¤?å®šä¹‰ViewHolder,å‡?å°‘ä»£ç ?é‡? ç”¨æ³•:
	 * 
	 * <pre>
	 * if (convertView == null)
	 * {
	 * 	convertView = View.inflate(context, R.layout.ad_demo, null);
	 * }
	 * TextView tv_demo = ViewHolderUtils.get(convertView, R.id.tv_demo);
	 * ImageView iv_demo = ViewHolderUtils.get(convertView, R.id.iv_demo);
	 * </pre>
	 */
	public static <T extends View> T hold(View view, int id)
	{
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();

		if (viewHolder == null)
		{
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}

		View childView = viewHolder.get(id);

		if (childView == null)
		{
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}

		return (T) childView;
	}

	/**
	 * æ›¿ä»£findviewByIdæ–¹æ³•
	 */
	public static <T extends View> T find(View view, int id)
	{
		return (T) view.findViewById(id);
	}
}
