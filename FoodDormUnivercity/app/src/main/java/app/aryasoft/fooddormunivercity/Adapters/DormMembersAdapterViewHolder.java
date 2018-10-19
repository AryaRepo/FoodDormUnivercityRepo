package app.aryasoft.fooddormunivercity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import app.aryasoft.fooddormunivercity.R;

public class DormMembersAdapterViewHolder extends RecyclerView.ViewHolder
{
    TextView txtStudentName;
    TextView txtStudentCode;
    Button btnDeliveryState;
    DormMembersAdapterViewHolder(@NonNull View itemView)
    {
        super(itemView);
        //-------------------
        txtStudentName=itemView.findViewById(R.id.txtStudentName);
        txtStudentCode=itemView.findViewById(R.id.txtStudentCode);
        btnDeliveryState=itemView.findViewById(R.id.btnDeliveryState);
    }
}



