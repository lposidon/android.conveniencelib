package posidon.android.conveniencelib

import android.content.res.Resources

inline fun Resources.dp(x: Number) = displayMetrics.density * x.toFloat()
inline fun Resources.sp(x: Number) = displayMetrics.density * x.toFloat()
