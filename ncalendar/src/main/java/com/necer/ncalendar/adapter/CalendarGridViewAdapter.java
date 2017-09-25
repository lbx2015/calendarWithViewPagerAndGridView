package com.necer.ncalendar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.necer.ncalendar.R;
import com.necer.ncalendar.utils.Attrs;
import com.necer.ncalendar.utils.Utils;
import com.necer.ncalendar.view.NMonthGridView;

import org.joda.time.DateTime;

import java.util.List;

/**
 * 日历gridview中的每一个item显示的textview
 * Created by zw.zhang on 2017/7/5.
 */

public class CalendarGridViewAdapter extends BaseAdapter {

    NMonthGridView nMonthView;
    Context context;
    private List<String> lunarList;
    private List<String> localDateList;
    private int mRowNum;
    private Drawable drawable = null;

    public CalendarGridViewAdapter(NMonthGridView nMonthView, Context context, DateTime dateTime) {
        nMonthView.init();
        this.context = context;
        this.nMonthView = nMonthView;
        nMonthView.mInitialDateTime = dateTime;

        //0周日，1周一
        Utils.NCalendar nCalendar2 = Utils.getMonthCalendar2(dateTime, Attrs.firstDayOfWeek);

        lunarList = nCalendar2.lunarList;
        localDateList = nCalendar2.localDateList;
        nMonthView.dateTimes = nCalendar2.dateTimeList;

        mRowNum = nMonthView.dateTimes.size() / 7;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        return dayNumber.length;
        return nMonthView.dateTimes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.d("zzw", "convertView : " + convertView);
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item, null);
        }

        DateTime dateTime = nMonthView.dateTimes.get(position);

        LinearLayout cellRoot = (LinearLayout) convertView.findViewById(R.id.root_linearLayout);
        TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
        TextView nTextView = (TextView) convertView.findViewById(R.id.n_tvtext);
        View point = convertView.findViewById(R.id.remind_circle);
        int daysOfCurrentMonth = nMonthView.mInitialDateTime.dayOfMonth().getMaximumValue();
        int firstWeekDayOfCurrentMonth = Utils.getWeekdayOfMonth(nMonthView.mInitialDateTime.getYear(), nMonthView.mInitialDateTime.getMonthOfYear());
        Log.d("zzw", "daysOfMonth: " + daysOfCurrentMonth);
        if (position >= firstWeekDayOfCurrentMonth && position < daysOfCurrentMonth + firstWeekDayOfCurrentMonth) {
            Log.d("zzw", "textView: " + textView + " dateTime.getDayOfMonth() " + dateTime.getDayOfMonth());
            textView.setText(dateTime.getDayOfMonth() + "");
            String lunar = lunarList.get(position);
            nTextView.setText(lunar);
            //show points
            point.setVisibility(View.VISIBLE);
        }
        Resources res = nMonthView.getResources();
        if ((nMonthView.mSelectDateTime == null && Utils.isToday(dateTime)) || (nMonthView.mSelectDateTime != null && dateTime.toLocalDate().equals(nMonthView.mSelectDateTime.toLocalDate()))) {
            // 设置当天的背景
            drawable = res.getDrawable(R.drawable.circular_textview);
            cellRoot.setBackgroundDrawable(drawable);
            textView.setTextColor(Color.WHITE);
            nTextView.setTextColor(Color.WHITE);
        } else {
            if (position >= firstWeekDayOfCurrentMonth && position < daysOfCurrentMonth + firstWeekDayOfCurrentMonth) {
                if (Utils.isToday(dateTime)) {
                    Log.d("zzw", "today: " + dateTime);
                    textView.setTextColor(res.getColor(R.color.color_29a1f7));// 当天字体
                    nTextView.setTextColor(res.getColor(R.color.color_29a1f7));
                } else {
                    //set the text color into b6b6b6 for sunday and saturday
                    if ((position % 7) == 0 || (position % 7) == 6) {
                        textView.setTextColor(res.getColor(R.color.color_background_b6b6b6));// 当月字体设黑
                        nTextView.setTextColor(res.getColor(R.color.color_background_b6b6b6));
                    } else {
                        // 当前月信息显示
                        textView.setTextColor(Color.BLACK);// 当月字体设黑
                        nTextView.setTextColor(Color.BLACK);
                    }
                }
                //remove the background
                cellRoot.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }
        }
        if (nMonthView.pointList != null)
            Log.d("zzw", "points: " + nMonthView.pointList.size());
//        if (nMonthView.pointList != null && nMonthView.pointList.contains(dateTime.toLocalDate().toString())) {
//            point.setVisibility(View.VISIBLE);
//        } else {
//            point.setVisibility(View.GONE);
//        }

        return convertView;
    }
}
