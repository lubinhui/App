package com.biyesheji.android.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyUtils {
	
	public static void jumpActivity(final Context context,Class cls){
		jumpActivity(context, cls, null, false);
	}
	public static void jumpActivity(final Context context, Class cls,
			Bundle bundle, boolean bTop) {
		Intent intent = new Intent();
		if (bundle != null) {
			intent.putExtras(bundle);
		}

		intent.setClass(context, cls);
		//if (bTop) {
			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//}

		context.startActivity(intent);
	}
}
