package com.necer.ncalendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.necer.ncalendar.listener.OnClickMonthViewListener;
import com.necer.ncalendar.view.NMonthGridView;

import org.joda.time.DateTime;

/**
 * Created by 闫彬彬 on 2017/8/28.
 * QQ:619008099
 */

public class NMonthGridAdapter extends NCalendarGridAdapter {

    private OnClickMonthViewListener mOnClickMonthViewListener;

    public NMonthGridAdapter(Context mContext, int count, int curr, DateTime dateTime, OnClickMonthViewListener onClickMonthViewListener) {
        super(mContext, count, curr, dateTime);
        this.mOnClickMonthViewListener = onClickMonthViewListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        NMonthGridView nMonthView = (NMonthGridView) mCalendarViews.get(position);
        DateTime dateTime = null;
        if (nMonthView == null) {
            int i = position - mCurr;
            dateTime = this.mDateTime.plusMonths(i);
            nMonthView = new NMonthGridView(mContext, dateTime);
            nMonthView.setNumColumns(7);
            mCalendarViews.put(position, nMonthView);
        }
        container.addView(nMonthView);
        CalendarGridViewAdapter calV = null;
        if (dateTime != null) {
            Log.d("zzw", "set adapter : " + dateTime);
            calV = new CalendarGridViewAdapter(nMonthView, container.getContext(), dateTime);
            calV.notifyDataSetChanged();
            nMonthView.setAdapter(calV);
        }
        final CalendarGridViewAdapter adapter = calV;

        final NMonthGridView monthView = nMonthView;
        //set item click listener
        nMonthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthView.mSelectDateTime = monthView.dateTimes.get(position);
                adapter.notifyDataSetChanged();
                Log.d("zzw", "onitemClic: " + " view " + view.getClass() + " parent: " + parent.getSelectedView());
            }
        });
        return nMonthView;
    }
}
