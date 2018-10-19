package app.aryasoft.fooddormunivercity.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import app.aryasoft.fooddormunivercity.Models.StudentModel;
import app.aryasoft.fooddormunivercity.R;

public class DormMembersAdapter extends RecyclerView.Adapter<DormMembersAdapterViewHolder>
{
    private Context context;
    private ArrayList<StudentModel> studentDataList;

    public DormMembersAdapter(Context context)
    {
        this.context = context;
        this.studentDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DormMembersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new DormMembersAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_dorm_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DormMembersAdapterViewHolder holder, int position)
    {
        if(studentDataList.size()==0)
            return;
        holder.txtStudentName.setText(studentDataList.get(position).StudentName);
        holder.txtStudentCode.setText(studentDataList.get(position).StudentCode);
        holder.btnDeliveryState.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!studentDataList.get(holder.getAdapterPosition()).ReserveState)
                {
                    Toast.makeText(context, "Reserved !", Toast.LENGTH_SHORT).show();
                    return;
                }

                StudentModel student = studentDataList.get(holder.getAdapterPosition());
                student.ReserveState = true;
                studentDataList.set(holder.getAdapterPosition(), student);
                notifyDataSetChanged();
                Toast.makeText(context, "Un Reserved !!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {

        //return studentDataList.size();
        return 5000;
    }

    public void addStudentDataList(ArrayList<StudentModel> studentDataList)
    {
        this.studentDataList.addAll(studentDataList);
        this.notifyDataSetChanged();
    }

}
