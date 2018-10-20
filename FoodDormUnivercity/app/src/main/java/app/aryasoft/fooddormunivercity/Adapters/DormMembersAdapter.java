package app.aryasoft.fooddormunivercity.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import java.util.ArrayList;
import app.aryasoft.fooddormunivercity.DbManager.DataAccessObject;
import app.aryasoft.fooddormunivercity.Models.Student;
import app.aryasoft.fooddormunivercity.R;
public class DormMembersAdapter extends RecyclerView.Adapter<DormMembersAdapterViewHolder>
{
    private Context context;
    private ArrayList<Student> studentDataList;
    private DataAccessObject dbContext;

    public DormMembersAdapter(Context context,DataAccessObject dbContext)
    {
        this.context = context;
        this.studentDataList = new ArrayList<>();
        this.dbContext = dbContext;
    }

    @NonNull
    @Override
    public DormMembersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new DormMembersAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_dorm_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DormMembersAdapterViewHolder holder, final int position)
    {

        if (studentDataList.size() == 0)
        {
            return;
        }
        if (studentDataList.get(position).ReserveState==1)
        {
            holder.btnDeliveryState.setBackground(context.getResources().getDrawable(R.drawable.btn_delivery_state));
            holder.btnDeliveryState.setText("تحویل داده شده");
        }
        else
        {
            holder.btnDeliveryState.setBackground(context.getResources().getDrawable(R.drawable.btn_no_delivery_state));
            holder.btnDeliveryState.setText("تحویل نشده");
        }
        holder.txtStudentName.setText(studentDataList.get(position).StudentName + " " + studentDataList.get(position).StudentFamily);
        holder.txtStudentCode.setText(studentDataList.get(position).StudentCode);
        holder.txtStudentFood.setText(studentDataList.get(position).StudentFoodName);
        holder.btnDeliveryState.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Student student = studentDataList.get(holder.getAdapterPosition());
                if (studentDataList.get(holder.getAdapterPosition()).ReserveState == 0)
                {
                    student.ReserveState = 1;
                    if (dbContext.addDeliveredReservation(student))
                    {
                        Toast.makeText(context, "رزرو تحویل داده شد.", Toast.LENGTH_SHORT).show();
                        studentDataList.set(holder.getAdapterPosition(), student);
                        holder.btnDeliveryState.setBackground(context.getResources().getDrawable(R.drawable.btn_delivery_state));
                        holder.btnDeliveryState.setText("تحویل داده شده");
                        //notifyDataSetChanged();
                    }
                    return;
                }
                student.ReserveState = 0;
                studentDataList.set(holder.getAdapterPosition(), student);
                holder.btnDeliveryState.setBackground(context.getResources().getDrawable(R.drawable.btn_no_delivery_state));
                holder.btnDeliveryState.setText("تحویل نشده");
                if (dbContext.removeDeliveredReservation(student.StudentId))
                {
                    Toast.makeText(context, "رزرو پس داده شد.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "not good", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_up_fade_in));

    }




    @Override
    public int getItemCount()
    {
        return studentDataList.size();
    }

    public void addStudentDataList(ArrayList<Student> studentDataList)
    {
        this.studentDataList.addAll(studentDataList);
        this.notifyDataSetChanged();
    }

    public void clearStudentDataList()
    {
        this.studentDataList.clear();
        this.notifyDataSetChanged();
    }
}
