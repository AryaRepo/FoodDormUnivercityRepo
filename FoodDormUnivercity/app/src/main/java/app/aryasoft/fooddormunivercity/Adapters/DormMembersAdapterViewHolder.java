package app.aryasoft.fooddormunivercity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import app.aryasoft.fooddormunivercity.R;

public class DormMembersAdapterViewHolder extends RecyclerView.ViewHolder
{
    TextView txtStudentName;
    TextView txtStudentCode;
    CheckBox chkDeliveryCheck;
    DormMembersAdapterViewHolder(@NonNull View itemView)
    {
        super(itemView);
        //-------------------
        txtStudentName=itemView.findViewById(R.id.txtStudentName);
        txtStudentCode=itemView.findViewById(R.id.txtStudentCode);
        chkDeliveryCheck=itemView.findViewById(R.id.chkDeliveryCheck);
    }
}



