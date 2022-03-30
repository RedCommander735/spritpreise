package org.woheller69.spritpreise.ui.RecycleList;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.woheller69.spritpreise.R;
import org.woheller69.spritpreise.database.Station;
import org.woheller69.spritpreise.ui.Help.StringFormatUtils;

import java.time.Instant;
import java.util.List;
import java.util.TimeZone;

//**
// * Created by yonjuni on 02.01.17.
// * Adapter for the horizontal listView for course of the day.
// */import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.CourseOfDayViewHolder> {

    private List<Station> courseOfDayList;
    private Context context;
    private TextView recyclerViewHeader;
    private RecyclerView recyclerView;


    StationAdapter(List<Station> courseOfDayList, Context context, TextView recyclerViewHeader, RecyclerView recyclerView) {
        this.context = context;
        this.courseOfDayList = courseOfDayList;
        this.recyclerViewHeader=recyclerViewHeader;
        this.recyclerView=recyclerView;
    }


    @Override
    public CourseOfDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_of_day, parent, false);
        return new CourseOfDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseOfDayViewHolder holder, int position) {

        if (courseOfDayList!=null && courseOfDayList.size()!=0 && courseOfDayList.get(0)!=null) {
            long time = courseOfDayList.get(0).getTimestamp();
            long zoneseconds = TimeZone.getDefault().getOffset(Instant.now().toEpochMilli()) / 1000L;
            long updateTime = ((time + zoneseconds) * 1000);
            recyclerViewHeader.setText(String.format("%s (%s)", context.getResources().getString(R.string.card_details_heading), StringFormatUtils.formatTimeWithoutZone(context, updateTime)));
        }

        holder.humidity.setText(Float.toString(courseOfDayList.get(position).getHumidity()));
        holder.temperature.setText( Float.toString(courseOfDayList.get(position).getTemperature()));
        holder.pressure.setText(Float.toString(courseOfDayList.get(position).getPressure()));
        holder.name.setText(courseOfDayList.get(position).getCity_name());


    }

    @Override
    public int getItemCount() {
        return courseOfDayList.size();
    }

    class CourseOfDayViewHolder extends RecyclerView.ViewHolder {
        TextView temperature;
        TextView humidity;
        TextView pressure;
        TextView name;

        CourseOfDayViewHolder(View itemView) {
            super(itemView);

            temperature = itemView.findViewById(R.id.course_of_day_temperature);
            humidity = itemView.findViewById(R.id.course_of_day_humidity);
            pressure = itemView.findViewById(R.id.course_of_day_pressure);
            name = itemView.findViewById(R.id.course_of_day_name);

        }
    }
}

