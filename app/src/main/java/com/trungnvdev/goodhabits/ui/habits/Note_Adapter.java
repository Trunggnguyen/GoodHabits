package com.trungnvdev.goodhabits.ui.habits;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.model.Note;

import java.util.List;

public class Note_Adapter extends RecyclerView.Adapter<Note_Adapter.ViewHolder>{

    List<Note> notes;
    Context context;
    HabitVewModel habitVewModel;

    public Note_Adapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new Note_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Note_Adapter.ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.time.setText(note.time);
        holder.note.setText(note.notes);
        holder.stt.setText((position+1)+"");
        if (position == notes.size()-1){
            holder.relativeLayout.setVisibility(View.GONE);
        }else {
            holder.relativeLayout.setVisibility(View.VISIBLE);
        }
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                popupMenu.dismiss();
                                final Dialog dialog = new Dialog( context);
                                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.dialog_addnote);

                                Window window = dialog.getWindow();
                                window.setLayout (WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) ;
                                window.setBackgroundDrawable (new ColorDrawable(Color. TRANSPARENT));

                                WindowManager.LayoutParams windowAttributes=  window.getAttributes();
                                windowAttributes.gravity  = Gravity.CENTER;
                                window.setAttributes (windowAttributes);

                                dialog.show();
                                TextView cacel = dialog.findViewById(R.id.edittexxtday);
                                TextView textViewok =dialog.findViewById(R.id.bt_ok);
                                EditText editText = dialog.findViewById(R.id.inputnote);
                                TextView textView = dialog.findViewById(R.id.titel);
                                textView.setText("Chỉnh sửa ghi chú của bạn !");
                                editText.setText(note.notes);
                                cacel.setOnClickListener(v1 -> {
                                    dialog.dismiss();
                                });
                                textViewok.setOnClickListener(v1 -> {
                                    habitVewModel = ViewModelProviders.of((FragmentActivity) context).get(HabitVewModel.class);
                                    if (editText.getText().toString().equals("")){

                                        Toast.makeText(context, "Ban chưa nhập nội dung!!!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Note note1 = new Note();
                                        note1.id_Habit= note.id_Habit;
                                        note1.idnote = note.idnote;
                                        note1.notes= editText.getText().toString();
                                        note1.time = note.time;
                                        habitVewModel.updateNote(note1);
                                        Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                                return true;
                            case R.id.delete:
                                habitVewModel = ViewModelProviders.of((FragmentActivity) context).get(HabitVewModel.class);
                                habitVewModel.deleteNote(note.idnote);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.pop_menu);
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView time,note, stt;
        ImageView more;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rela);
            stt = itemView.findViewById(R.id.stt);
            more = itemView.findViewById(R.id.icmore);
            time = itemView.findViewById(R.id.time);
            note = itemView.findViewById(R.id.note);

        }
    }
}
